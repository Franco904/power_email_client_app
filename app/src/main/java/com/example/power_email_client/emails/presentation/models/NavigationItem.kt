package com.example.power_email_client.emails.presentation.models

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Drafts
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material.icons.filled.Report
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.power_email_client.R
import com.example.power_email_client.core.domain.models.MailboxType

enum class NavigationItem(
    val mailboxType: MailboxType,
    val icon: ImageVector,
    @StringRes val text: Int,
) {
    Inbox(
        mailboxType = MailboxType.Inbox,
        icon = Icons.Default.Inbox,
        text = R.string.tab_inbox,
    ),
    Sent(
        mailboxType = MailboxType.Sent,
        icon = Icons.AutoMirrored.Filled.Send,
        text = R.string.tab_sent,
    ),
    Drafts(
        mailboxType = MailboxType.Drafts,
        icon = Icons.Default.Drafts,
        text = R.string.tab_drafts,
    ),
    Spam(
        mailboxType = MailboxType.Spam,
        icon = Icons.Default.Report,
        text = R.string.tab_spam,
    ),
}
