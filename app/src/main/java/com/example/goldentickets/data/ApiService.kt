package com.example.goldentickets.data

import com.example.goldentickets.data.models.*
import retrofit2.Response
import retrofit2.http.*

const val BASE_URL = "http://api.pninformatica.com.py:3000"

interface ApiService {
    @POST("/api/auth/login")
    suspend fun login(@Body credentials: LoginRequest): Response<LoginResponse>

    @POST("/api/auth/google")
    suspend fun googleLogin(@Body token: GoogleTokenRequest): Response<LoginResponse>

    @GET("/api/events")
    suspend fun getEvents(): List<Event>

    @POST("/api/reservations")
    suspend fun createReservation(@Body reservation: Map<String, Any>): Response<Reservation>

    @GET("/api/reservations/{userId}")
    suspend fun getReservations(@Path("userId") userId: Int): List<Reservation>
}
