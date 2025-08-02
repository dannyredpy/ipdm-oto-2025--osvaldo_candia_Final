package com.example.goldentickets.data

import com.example.goldentickets.data.models.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Repository {
    private val api: ApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(ApiService::class.java)
    }

    suspend fun login(email: String, password: String) =
        api.login(LoginRequest(email, password))

    suspend fun googleLogin(idToken: String) =
        api.googleLogin(GoogleTokenRequest(idToken))

    suspend fun getEvents() = api.getEvents()

    suspend fun createReservation(eventId: Int, userId: Int, quantity: Int) =
        api.createReservation(mapOf("event_id" to eventId, "user_id" to userId, "quantity" to quantity))

    suspend fun getReservations(userId: Int) = api.getReservations(userId)
}
