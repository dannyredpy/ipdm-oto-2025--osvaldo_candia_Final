package com.example.goldentickets.data.models

data class Reservation(
    val id: Int,
    val eventId: Int,
    val userId: Int,
    val eventTitle: String,
    val date: String,
    val quantity: Int
)
