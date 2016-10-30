package com.example.app.data.repository.base

import rx.Emitter
import rx.Observable

/**
 * Repository pattern implementation. This class implements all the data handling logic based on
 * different data sources. Abstracts the data origin and works as a processor cache system where
 * different data sources are going to work as different cache levels. It coordinates three
 * different types of data sources, [ReadableDataSource], [WriteableDataSource]
 * @param  Class representing the key used to identify items in this repository
 * @param  Class representing the contents of the items held by this repository
 */
abstract class BaseRepository<K, V : Identifiable<K>> :
        ReadableDataSource<K, V>, WriteableDataSource<K, V> {

    lateinit var readableDataSources: Collection<ReadableDataSource<K, V>>
    lateinit var writeableDataSources: Collection<WriteableDataSource<K, V>>

    /**
     * [ReadableDataSource.get]
     */
    override fun getElement(key: K): Observable<V> {
        return Observable.fromEmitter<V>(
                { emitter ->
                    Observable.from(readableDataSources)
                            .flatMap { source -> source.getElement(key) }
                            .first()
                            .subscribe(
                                    { s -> emitter.onNext(s) },
                                    { t -> emitter.onError(t) },
                                    { emitter.onCompleted() }
                            )
                }, Emitter.BackpressureMode.BUFFER)
    }

    /**
     * [ReadableDataSource.getAll]
     */
    override fun getAllElements(): Observable<Collection<V>> {
        return Observable.fromEmitter<Collection<V>>(
                { emitter ->
                    Observable.from(readableDataSources)
                            .flatMap { source -> source.getAllElements() }
                            .first()
                            .subscribe(
                                    { s -> emitter.onNext(s) },
                                    { t -> emitter.onError(t) },
                                    { emitter.onCompleted() }
                            )
                }, Emitter.BackpressureMode.BUFFER)
    }

    /**
     * [WriteableDataSource.put]
     */
    override fun putElement(value: V): Observable<V> {
        return Observable.fromEmitter<V>(
                { emitter ->
                    Observable.from(writeableDataSources)
                            .flatMap { source -> source.putElement(value) }
                            .subscribe(
                                    { s -> emitter.onNext(s) },
                                    { t -> emitter.onError(t) },
                                    { emitter.onCompleted() }
                            )
                }, Emitter.BackpressureMode.BUFFER)
    }

    /**
     * [WriteableDataSource.putAll]
     */
    override fun putAllElements(values: Collection<V>): Observable<Collection<V>> {
        return Observable.fromEmitter<Collection<V>>(
                { emitter ->
                    Observable.from(writeableDataSources)
                            .flatMap { source -> source.putAllElements(values) }
                            .subscribe(
                                    { s -> emitter.onNext(s) },
                                    { t -> emitter.onError(t) },
                                    { emitter.onCompleted() }
                            )
                }, Emitter.BackpressureMode.BUFFER)
    }

    /**
     * [WriteableDataSource.delete]
     */
    override fun deleteElement(key: K): Observable<Any> {
        return Observable.fromEmitter<Any>(
                { emitter ->
                    Observable.from(writeableDataSources)
                            .flatMap { source -> source.deleteElement(key) }
                            .subscribe(
                                    { s -> emitter.onNext(s) },
                                    { t -> emitter.onError(t) },
                                    { emitter.onCompleted() }
                            )
                }, Emitter.BackpressureMode.BUFFER)
    }

    /**
     * [WriteableDataSource.deleteAll]
     */
    override fun deleteAllElements(): Observable<Any> {
        return Observable.fromEmitter<Any>(
                { emitter ->
                    Observable.from(writeableDataSources)
                            .flatMap { source -> source.deleteAllElements() }
                            .subscribe(
                                    { s -> emitter.onNext(s) },
                                    { t -> emitter.onError(t) },
                                    { emitter.onCompleted() }
                            )
                }, Emitter.BackpressureMode.BUFFER)
    }
}