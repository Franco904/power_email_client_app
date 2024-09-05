package com.example.power_email_client.emails.presentation

import androidx.lifecycle.ViewModel
import com.example.power_email_client.core.domain.models.MailboxType
import com.example.power_email_client.emails.data.EmailsRepositoryImpl
import com.example.power_email_client.emails.domain.EmailsRepository
import com.example.power_email_client.emails.presentation.models.EmailUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class EmailsViewModel(
    private val emailsRepository: EmailsRepository = EmailsRepositoryImpl(),
) : ViewModel() {
    private val emailsByMailboxType: Map<MailboxType, List<EmailUiState>> =
        emailsRepository.findAll()
            .map { EmailUiState.fromEmail(it) }
            .groupBy { it.currentMailbox }

    private val _currentEmails = MutableStateFlow<List<EmailUiState>>(emptyList())
    val currentEmails = _currentEmails.asStateFlow()

    private val _currentMailboxType = MutableStateFlow(MailboxType.Inbox)
    val currentMailboxType = _currentMailboxType.asStateFlow()

    private val _selectedEmail = MutableStateFlow<EmailUiState?>(null)
    val selectedEmail = _selectedEmail.asStateFlow()

    fun init(selectedEmailId: Long?) {
        _currentEmails.update {
            emailsByMailboxType[currentMailboxType.value] ?: emptyList()
        }

        onEmailSelected(selectedEmailId)
    }

    fun onEmailSelected(selectedEmailId: Long?) {
        _selectedEmail.update { current ->
            if (selectedEmailId == null || selectedEmailId == current?.id) null
            else {
                val email = emailsRepository.findById(selectedEmailId)
                EmailUiState.fromEmail(email)
            }
        }
    }

    fun onNavItemSelected(tabMailboxType: MailboxType) {
        _currentEmails.update { emailsByMailboxType[tabMailboxType] ?: emptyList() }
        _currentMailboxType.update { tabMailboxType }
        _selectedEmail.update { null }
    }
}
