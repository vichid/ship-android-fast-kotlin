package com.example.app.data.repository.base

import rx.Observable

/**
 * Data source interface meant to be used only to retrieve data.

 * @param  The class of the key used by this data source.
 * *
 * @param  The class of the values retrieved from this data source.
 */
interface ReadableDataSource<in K, V> {
    /**
     * Returns the only value that is uniquely identified by the provided key or null if there is
     * no value associated to it.

     * @param key The key that uniquely identifies the requested value.
     * *
     * @return The value associated to the provided key or null if there is not any.
     */
    fun getElement(key: K): Observable<V>

    /**
     * Returns all the values available in the data source or null if the operation does not make
     * sense in the context of the data source.

     * @return A collection of values or null if the operation is not implemented by this data source.
     */
    fun getAllElements(): Observable<Collection<V>>
}