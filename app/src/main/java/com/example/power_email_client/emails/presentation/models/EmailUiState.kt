package com.example.power_email_client.emails.presentation.models

import androidx.annotation.DrawableRes
import com.example.power_email_client.core.domain.models.Email
import com.example.power_email_client.core.domain.models.MailboxType
import com.example.power_email_client.core.presentation.utils.toPresentationText

data class EmailUiState(
    val id: Long,
    @DrawableRes val senderPictureId: Int,
    val senderFullName: String,
    val createdAt: String,
    val subject: String? = null,
    val body: String,
    val currentMailbox: MailboxType,
) {
    companion object {
        fun fromEmail(email: Email) = EmailUiState(
            id = email.id,
            senderPictureId = email.sender.pictureId,
            senderFullName = email.sender.fullName,
            createdAt = email.createdAtTimestamp.toPresentationText(),
            subject = email.subject,
            body = email.body,
            currentMailbox = email.currentMailbox,
        )
    }
}
