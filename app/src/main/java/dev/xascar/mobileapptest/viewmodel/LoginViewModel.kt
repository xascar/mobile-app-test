package dev.xascar.mobileapptest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.xascar.mobileapptest.domain.RegistrationFormDomain
import dev.xascar.mobileapptest.model.RegistrationResponse
import dev.xascar.mobileapptest.model.UserRegistration
import dev.xascar.mobileapptest.repository.PlatonRepository
import dev.xascar.mobileapptest.util.ResultState
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val platonRepository: PlatonRepository
) : ViewModel() {

    private var _registration = MutableLiveData<List<RegistrationFormDomain<Any>>>()
    val registration: LiveData<List<RegistrationFormDomain<Any>>> = _registration

    private var _message = MutableLiveData("")
    val message: LiveData<String> = _message

    fun getRegistrationFields(user: UserRegistration) {

        viewModelScope.launch {

            when (val response = platonRepository.getRegistrationFields(user)){
                ResultState.Loading -> {
                    _message.value = ""
                }
                is ResultState.Success -> {
                    _message.value = "Datos de registro recuperados: ${response.data}"
                    _registration.value = response.data
                }
                is ResultState.Error -> {
                    _message.value = response.exception.localizedMessage
                }
            }


        }

    }

}