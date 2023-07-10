package dev.xascar.mobileapptest.remote

import dev.xascar.mobileapptest.model.RegistrationResponse
import dev.xascar.mobileapptest.model.UserRegistration
import dev.xascar.mobileapptest.util.ENDPOINT
import dev.xascar.mobileapptest.util.HEADERS
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface PlatonService {
    @Headers(HEADERS)
    @POST(ENDPOINT)
    suspend fun getRegistrationFields(@Body user: UserRegistration): Response<RegistrationResponse>
}