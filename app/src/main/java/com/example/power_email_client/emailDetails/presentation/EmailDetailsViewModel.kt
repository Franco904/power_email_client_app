package com.example.power_email_client.emailDetails.presentation

import androidx.lifecycle.ViewModel
import com.example.power_email_client.emailDetails.presentation.models.EmailDetailsUiState
import com.example.power_email_client.emails.data.EmailsRepositoryImpl
import com.example.power_email_client.emails.domain.EmailsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class EmailDetailsViewModel(
    private val emailsRepository: EmailsRepository = EmailsRepositoryImpl(),
) : ViewModel() {
    private val _email = MutableStateFlow<EmailDetailsUiState?>(null)
    val email = _email.asStateFlow()

    fun init(emailId: Long) {
        emailsRepository.findById(id = emailId).let { storedEmail ->
            _email.update { EmailDetailsUiState.fromEmail(storedEmail) }
        }
    }
}
