package com.example.power_email_client.emails.data

import com.example.power_email_client.emails.data.local.EmailsLocalProvider
import com.example.power_email_client.emails.domain.EmailsRepository

class EmailsRepositoryImpl : EmailsRepository {
    override fun findAll() = EmailsLocalProvider.findAll()
}