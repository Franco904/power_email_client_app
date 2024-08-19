package com.example.power_email_client.emails.presentation

import androidx.lifecycle.ViewModel
import com.example.power_email_client.core.domain.models.Email
import com.example.power_email_client.core.domain.models.MailboxType
import com.example.power_email_client.core.presentation.utils.toPresentationText
import com.example.power_email_client.emails.data.EmailsRepositoryImpl
import com.example.power_email_client.emails.domain.EmailsRepository
import com.example.power_email_client.emails.presentation.models.EmailUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class EmailsViewModel(
    emailsRepository: EmailsRepository = EmailsRepositoryImpl(),
) : ViewModel() {
    private val emailsByMailboxType: Map<MailboxType, List<EmailUiState>> = emailsRepository.findAll()
        .map { it.toUiState() }
        .groupBy { it.currentMailbox }

    private val _currentEmails = MutableStateFlow<List<EmailUiState>>(emptyList())
    val currentEmails = _currentEmails.asStateFlow()

    private val _currentMailboxType = MutableStateFlow(MailboxType.Inbox)
    val currentMailboxType = _currentMailboxType.asStateFlow()

    init {
        _currentEmails.update {
            emailsByMailboxType[currentMailboxType.value] ?: emptyList()
        }
    }

    private fun Email.toUiState(): EmailUiState {
        return EmailUiState(
            id = id,
            senderPictureId = sender.pictureId,
            senderFullName = sender.fullName,
            createdAt = createdAtTimestamp.toPresentationText(),
            subject = subject,
            body = body,
            currentMailbox = currentMailbox,
        )
    }

    fun onBarItemSelected(tabMailboxType: MailboxType) {
        _currentMailboxType.update { tabMailboxType }
        _currentEmails.update { emailsByMailboxType[tabMailboxType] ?: emptyList() }
    }
}