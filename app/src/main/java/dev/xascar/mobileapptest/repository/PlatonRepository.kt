package dev.xascar.mobileapptest.repository

import android.util.Log
import dev.xascar.mobileapptest.domain.RegistrationFormDomain
import dev.xascar.mobileapptest.domain.mapToDomain
import dev.xascar.mobileapptest.model.UserRegistration
import dev.xascar.mobileapptest.remote.PlatonService
import dev.xascar.mobileapptest.util.ResultState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface PlatonRepository{
    suspend fun getRegistrationFields(user: UserRegistration): ResultState<List<RegistrationFormDomain<Any>>>
}

private const val TAG = "PlatonRepository"
class PlatonRepositoryImpl @Inject constructor(private val networkApiService: PlatonService,
                                           private val ioDispatcher: CoroutineDispatcher
): PlatonRepository{

    override suspend fun getRegistrationFields(user: UserRegistration): ResultState<List<RegistrationFormDomain<Any>>> =

        try{
            withContext(ioDispatcher){

                val response = networkApiService.getRegistrationFields(user)
                response.body()?.let {
                    Log.d(TAG, "getRegistrationFields: $it")
                    ResultState.Success(it.data.mapToDomain())
                }?: throw Exception("Empty response")
            }

        }catch (e: Exception){
            Log.d(TAG, "getRegistrationFields: $e")
            ResultState.Error(e)
        }


}