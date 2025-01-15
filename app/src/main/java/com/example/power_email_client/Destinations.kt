package com.example.power_email_client

import kotlinx.serialization.Serializable

sealed interface Destinations {
    @Serializable
    data object Emails

    @Serializable
    data class EmailDetails(val emailId: Long)
}
