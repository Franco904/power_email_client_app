package com.example.power_email_client.emails.domain

import com.example.power_email_client.core.domain.models.Email

interface EmailsRepository {
    fun findAll(): List<Email>
}