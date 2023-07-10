package dev.xascar.mobileapptest.util

import dev.xascar.mobileapptest.domain.RegistrationFormDomain

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class ResultState {
    object Loading: ResultState()
    data class Success(val data: List<RegistrationFormDomain>) : ResultState()
    data class Error(val exception: Exception) : ResultState()
}
