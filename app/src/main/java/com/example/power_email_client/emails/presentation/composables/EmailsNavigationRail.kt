package com.example.power_email_client.emails.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.power_email_client.R
import com.example.power_email_client.core.domain.models.MailboxType
import com.example.power_email_client.emails.presentation.models.NavigationItem

@Composable
fun EmailsNavigationRail(
    currentTabMailboxType: MailboxType,
    onRailItemSelected: (MailboxType) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationRail(
        modifier = modifier
            .testTag(stringResource(id = R.string.navigation_rail))
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxHeight()
        ) {
            Spacer(modifier = Modifier)
            for (navItem in NavigationItem.entries) {
                EmailsNavigationRailItem(
                    navItem = navItem,
                    currentTabMailboxType = currentTabMailboxType,
                    onRailItemSelected = onRailItemSelected,
                )
            }
        }
    }
}

@Composable
private fun EmailsNavigationRailItem(
    navItem: NavigationItem,
    currentTabMailboxType: MailboxType,
    onRailItemSelected: (MailboxType) -> Unit
) {
    NavigationRailItem(
        selected = navItem.mailboxType == currentTabMailboxType,
        onClick = { onRailItemSelected(navItem.mailboxType) },
        icon = {
            Icon(
                imageVector = navItem.icon,
                contentDescription = stringResource(id = navItem.text),
            )
        },
        label = { Text(stringResource(id = navItem.text)) }
    )
}
