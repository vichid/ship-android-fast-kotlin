package com.example.app.domain.repository.base

import com.example.app.data.repository.base.Identifiable
import rx.Observable

/**
 * Data source interface meant to be used only to persist data.

 * @param  The class of the key used by this data source.
 * *
 * @param  The class of the values stored into this data source.
 */
interface WriteableDataSource<K, V : Identifiable<K>> {
    /**
     * Adds or update the provided value into this data source.

     * @param value The value to be persisted.
     * *
     * @return The value after its addition or update.
     */
    fun put(value: V): Observable<V>

    /**
     * Add or updates all the provided values into this data source.

     * @param values A collection of values to be added or persisted.
     * *
     * @return The values that has been persisted.
     */
    fun putAll(values: Collection<V>): Observable<Collection<V>>

    /**
     * Deletes a value given its associated key.

     * @param key The key that uniquely identifies the value to be deleted.
     */
    fun delete(key: K): Observable<Any>

    /**
     * Delete all the values stored in this data source.
     */
    fun deleteAll(): Observable<Any>
}
