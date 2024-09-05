package com.example.power_email_client.core.domain.models

data class Account(
    val id: Long,
    val fullName: String,
    val email: String,
    val pictureId: Int,
)
