package com.example.power_email_client.emails.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.power_email_client.core.domain.models.MailboxType
import com.example.power_email_client.emails.presentation.models.NavigationItem

@Composable
fun EmailsNavigationDrawer(
    currentTabMailboxType: MailboxType,
    onDrawerItemSelected: (MailboxType) -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    PermanentNavigationDrawer(
        drawerContent = {
            PermanentDrawerSheet(
                modifier = Modifier
                    .width(280.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    EmailsNavigationDrawerHeader()
                    Spacer(modifier = Modifier.height(16.dp))
                    for (navItem in NavigationItem.entries) {
                        EmailsNavigationDrawerItem(
                            navItemMailboxType = navItem.mailboxType,
                            navItemIcon = navItem.icon,
                            navItemText = navItem.text,
                            currentTabMailboxType = currentTabMailboxType,
                            onDrawerItemSelected = onDrawerItemSelected,
                        )
                    }
                }
            }
        },
        modifier = modifier
    ) {
        content()
    }
}

@Composable
private fun EmailsNavigationDrawerHeader() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(16.dp)
    ) {
        ReplyTitle(
            modifier = Modifier
                .weight(1f)
        )
        AccountRoundedPicture()
    }
}

@Composable
private fun EmailsNavigationDrawerItem(
    navItemMailboxType: MailboxType,
    navItemIcon: ImageVector,
    navItemText: Int,
    currentTabMailboxType: MailboxType,
    onDrawerItemSelected: (MailboxType) -> Unit
) {
    NavigationDrawerItem(
        selected = navItemMailboxType == currentTabMailboxType,
        onClick = { onDrawerItemSelected(navItemMailboxType) },
        icon = {
            Icon(
                imageVector = navItemIcon,
                contentDescription = stringResource(id = navItemText),
            )
        },
        label = { Text(stringResource(id = navItemText)) },
        colors = NavigationDrawerItemDefaults.colors(
            unselectedContainerColor = Color.Transparent,
        ),
        modifier = Modifier
            .padding(NavigationDrawerItemDefaults.ItemPadding)
    )
}
