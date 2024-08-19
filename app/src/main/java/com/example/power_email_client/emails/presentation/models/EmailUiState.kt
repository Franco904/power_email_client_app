package com.example.power_email_client.emails.presentation.models

import androidx.annotation.DrawableRes
import com.example.power_email_client.core.domain.models.MailboxType

data class EmailUiState(
    val id: Long,
    @DrawableRes val senderPictureId: Int,
    val senderFullName: String,
    val createdAt: String,
    val subject: String,
    val body: String,
    val currentMailbox: MailboxType,
)
