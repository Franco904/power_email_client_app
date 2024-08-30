package com.example.power_email_client.emails.presentation.composables

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.power_email_client.core.domain.models.MailboxType
import com.example.power_email_client.emails.presentation.models.NavigationItem

@Composable
fun EmailsBottomNavigationBar(
    currentTabMailboxType: MailboxType,
    onBarItemSelected: (MailboxType) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
        modifier = modifier
    ) {
        for (navItem in NavigationItem.entries) {
            NavigationBarItem(
                selected = navItem.mailboxType == currentTabMailboxType,
                onClick = { onBarItemSelected(navItem.mailboxType) },
                icon = {
                    Icon(
                        imageVector = navItem.icon,
                        contentDescription = stringResource(id = navItem.text),
                    )
                },
                label = { Text(stringResource(id = navItem.text)) }
            )
        }
    }
}
