package dev.xascar.mobileapptest.util

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class ResultState<out T : Any> {

    object Loading: ResultState<Nothing>()
    data class Success<out T : Any>(val data: T) : ResultState<T>()
    data class Error(val exception: Exception) : ResultState<Nothing>()

}
