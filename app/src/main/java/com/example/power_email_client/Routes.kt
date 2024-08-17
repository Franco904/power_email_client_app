package com.example.power_email_client

import kotlinx.serialization.Serializable

sealed interface Routes {
    @Serializable
    data object Emails

    @Serializable
    data object EmailDetails
}
