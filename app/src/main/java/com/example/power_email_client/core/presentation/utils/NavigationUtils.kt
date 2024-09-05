package com.example.power_email_client.core.presentation.utils

import androidx.navigation.NavBackStackEntry

const val EMAIL_ID_FROM_DETAILS_KEY = "emailIdFromDetailsScreen"

fun NavBackStackEntry.getEmailIdFromDetails(): Long? {
    return savedStateHandle[EMAIL_ID_FROM_DETAILS_KEY]
}

fun NavBackStackEntry.setEmailIdFromDetails(emailId: Long?) {
    savedStateHandle[EMAIL_ID_FROM_DETAILS_KEY] = emailId
}
