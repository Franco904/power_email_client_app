package com.example.power_email_client.core.domain.models

data class Email(
    val id: Long,
    val sender: Account,
    val recipients: List<Account> = emptyList(),
    val subject: String? = null,
    val body: String,
    val currentMailbox: MailboxType = MailboxType.Inbox,
    val createdAtTimestamp: Long,
)
