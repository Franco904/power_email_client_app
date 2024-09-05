package com.example.power_email_client.emailDetails.presentation.models

import androidx.annotation.DrawableRes
import com.example.power_email_client.core.domain.models.Email
import com.example.power_email_client.core.presentation.utils.toPresentationText
import com.example.power_email_client.emails.presentation.models.EmailUiState

data class EmailDetailsUiState(
    val id: Long,
    @DrawableRes val senderPictureId: Int,
    val senderFullName: String,
    val createdAt: String,
    val subject: String? = null,
    val body: String,
) {
    companion object {
        fun fromEmail(email: Email) = EmailDetailsUiState(
            id = email.id,
            senderPictureId = email.sender.pictureId,
            senderFullName = email.sender.fullName,
            createdAt = email.createdAtTimestamp.toPresentationText(),
            subject = email.subject,
            body = email.body,
        )

        fun fromEmailUiState(emailUiState: EmailUiState) = EmailDetailsUiState(
            id = emailUiState.id,
            senderPictureId = emailUiState.senderPictureId,
            senderFullName = emailUiState.senderFullName,
            createdAt = emailUiState.createdAt,
            body = emailUiState.body,
        )
    }
}
