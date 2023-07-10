package dev.xascar.mobileapptest.repository

import android.util.Log
import dev.xascar.mobileapptest.data.RegistrationDAO
import dev.xascar.mobileapptest.domain.RegistrationFormDomain
import dev.xascar.mobileapptest.domain.mapToDomain
import dev.xascar.mobileapptest.model.UserRegistration
import dev.xascar.mobileapptest.remote.PlatonService
import dev.xascar.mobileapptest.util.ResultState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface PlatonRepository {
    suspend fun getRegistrationFields(user: UserRegistration): ResultState
}

private const val TAG = "PlatonRepository"

class PlatonRepositoryImpl @Inject constructor(
    private val networkApiService: PlatonService,
    private val ioDispatcher: CoroutineDispatcher,
    private val registrationDAO: RegistrationDAO
) : PlatonRepository {

    override suspend fun getRegistrationFields(user: UserRegistration): ResultState =

        try {
            withContext(ioDispatcher) {

                val response = networkApiService.getRegistrationFields(user)
                response.body()?.let {
                    Log.d(TAG, "getRegistrationFields: $it")
                    //Updating local data
                    registrationDAO.clearRegistrationFields()
                    //Saving to local room db
                    registrationDAO.insertRegistrationFields(it.data.mapToDomain())
                    //Fetching data from local storage
                    ResultState.Success(registrationDAO.getAllRegistrationFields())
                } ?: throw Exception("Empty response")
            }

        } catch (e: Exception) {
            try {
                //Fetching data from local storage
                Log.d(TAG, "Fetching data offline mode")
                ResultState.Success(registrationDAO.getAllRegistrationFields())
            }catch (e: Exception){
                Log.d(TAG, "getRegistrationFields: $e")
                ResultState.Error(e)
            }
        }
}