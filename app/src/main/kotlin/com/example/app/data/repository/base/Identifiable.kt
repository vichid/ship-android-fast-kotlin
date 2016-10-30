package com.example.app.data.repository.base

/**
 * Represents an object that can be identified uniquely by an object of the parametrized class.

 * @param  The class of the key used to identify objects of this class.
 */
interface Identifiable<out K> {
    fun getKey(): K
}