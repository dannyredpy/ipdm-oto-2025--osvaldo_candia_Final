package com.example.goldentickets.data.models

data class Event(
    val id: Int,
    val title: String,
    val category: String,
    val date: String,
    val time: String,
    val price: Int,
    val image_url: String?
)
