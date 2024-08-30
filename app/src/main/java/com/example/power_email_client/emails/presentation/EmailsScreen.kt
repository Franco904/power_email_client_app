package com.example.power_email_client.emails.presentation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.power_email_client.R
import com.example.power_email_client.core.domain.models.MailboxType
import com.example.power_email_client.core.presentation.theme.AppTheme
import com.example.power_email_client.emailDetails.presentation.EmailDetailsCard
import com.example.power_email_client.emailDetails.presentation.models.EmailDetailsUiState
import com.example.power_email_client.emails.presentation.composables.EmailsBottomNavigationBar
import com.example.power_email_client.emails.presentation.composables.EmailsNavigationDrawer
import com.example.power_email_client.emails.presentation.composables.EmailsNavigationRail
import com.example.power_email_client.emails.presentation.composables.EmailsTopAppBar
import com.example.power_email_client.emails.presentation.models.EmailUiState
import com.example.power_email_client.emails.presentation.models.NavigationComposableType

@Composable
fun EmailsScreen(
    modifier: Modifier = Modifier,
    viewModel: EmailsViewModel = viewModel(),
    onEmailSelectedToDetailsScreen: (Long) -> Unit = {},
    windowSize: WindowWidthSizeClass = WindowWidthSizeClass.Compact,
) {
    val emails by viewModel.currentEmails.collectAsStateWithLifecycle()
    val mailboxType by viewModel.currentMailboxType.collectAsStateWithLifecycle()

    val navigationComposableType = getNavigationComposableType(windowSize)

    if (navigationComposableType == NavigationComposableType.PermanentNavigationDrawer) {
        var selectedEmail: EmailUiState? by remember { mutableStateOf(null) }

        EmailsNavigationDrawer(
            currentTabMailboxType = mailboxType,
            onDrawerItemSelected = { itemMailBoxType ->
                selectedEmail = null
                viewModel.onNavItemSelected(itemMailBoxType)
            },
        ) {
            EmailsContent(
                emails = emails,
                selectedEmail = selectedEmail,
                onEmailSelectedToDetailsCard = { email ->
                    selectedEmail = if (selectedEmail == email) null else email
                },
                modifier = modifier
            )
        }
    } else {
        EmailsContent(
            emails = emails,
            currentTabMailboxType = mailboxType,
            navigationComposableType = navigationComposableType,
            onNavItemSelected = viewModel::onNavItemSelected,
            onEmailSelectedToDetailsScreen = onEmailSelectedToDetailsScreen,
            modifier = modifier
        )
    }
}

@Composable
private fun EmailsContent(
    emails: List<EmailUiState>,
    selectedEmail: EmailUiState?,
    onEmailSelectedToDetailsCard: (EmailUiState) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {
        EmailsList(
            emails = emails,
            onEmailSelected = onEmailSelectedToDetailsCard,
            selectedEmail = selectedEmail,
            modifier = Modifier
                .padding(top = 4.dp)
                .weight(1f)
        )
        EmailDetailsCard(
            email = if (selectedEmail == null) null else EmailDetailsUiState.fromEmailUiState(selectedEmail),
            modifier = Modifier
                .padding(top = 12.dp)
                .weight(1f)
        )
        Spacer(modifier = Modifier.width(4.dp))
    }
}

@Composable
private fun EmailsContent(
    emails: List<EmailUiState>,
    currentTabMailboxType: MailboxType,
    navigationComposableType: NavigationComposableType,
    onNavItemSelected: (MailboxType) -> Unit,
    onEmailSelectedToDetailsScreen: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    val showTopAppBar = navigationComposableType in listOf(
        NavigationComposableType.BottomNavigationBar,
        NavigationComposableType.NavigationRail,
    )

    Scaffold(
        topBar = {
            if (showTopAppBar) EmailsTopAppBar()
        },
        bottomBar = {
            if (navigationComposableType == NavigationComposableType.BottomNavigationBar) {
                EmailsBottomNavigationBar(
                    currentTabMailboxType = currentTabMailboxType,
                    onBarItemSelected = onNavItemSelected,
                )
            }
        },
        modifier = modifier
    ) { innerPadding ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            if (navigationComposableType == NavigationComposableType.NavigationRail) {
                Surface(
                    modifier = modifier
                        .fillMaxHeight()
                        .background(MaterialTheme.colorScheme.outlineVariant)
                        .offset(x = (-1).dp)
                ) {
                    EmailsNavigationRail(
                        currentTabMailboxType = currentTabMailboxType,
                        onRailItemSelected = onNavItemSelected,
                        modifier = Modifier
                            .padding(
                                top = innerPadding.calculateTopPadding(),
                                bottom = innerPadding.calculateBottomPadding(),
                            )
                    )
                }
            }
            EmailsList(
                emails = emails,
                onEmailSelected = { email -> onEmailSelectedToDetailsScreen(email.id) },
                modifier = Modifier
                    .padding(
                        top = innerPadding.calculateTopPadding() + 4.dp,
                        bottom = innerPadding.calculateBottomPadding(),
                    )
                    .weight(1f)
            )
        }
    }
}

@Composable
fun EmailsList(
    emails: List<EmailUiState>,
    onEmailSelected: (EmailUiState) -> Unit,
    modifier: Modifier = Modifier,
    selectedEmail: EmailUiState? = null,
) {
    // TODO: Build empty screens for each mailbox type
    LazyColumn(
        contentPadding = PaddingValues(
            horizontal = 16.dp,
            vertical = 8.dp,
        ),
        modifier = modifier
    ) {
        items(
            items = emails,
            contentType = { EmailUiState::class },
            key = { email -> email.id }
        ) { email ->
            EmailItem(
                email = email,
                onEmailSelected = onEmailSelected,
                isSelected = email == selectedEmail,
            )
            if (email != emails.last()) {
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
private fun EmailItem(
    email: EmailUiState,
    onEmailSelected: (EmailUiState) -> Unit,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
) {
    val cardColor = if (isSelected) {
        MaterialTheme.colorScheme.inversePrimary
    } else MaterialTheme.colorScheme.secondaryContainer

    Card(
        colors = CardDefaults.cardColors(containerColor = cardColor),
        onClick = { onEmailSelected(email) },
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.email_list_item_inner_padding))
        ) {
            EmailItemHeader(email)
            EmailItemBody(email)
        }
    }
}

@Composable
fun EmailItemHeader(
    email: EmailUiState,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {
        SenderAccountProfileImage(
            drawableResource = email.senderPictureId,
            contentDescription = email.senderFullName,
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(
                    horizontal = dimensionResource(R.dimen.email_header_content_padding_horizontal),
                    vertical = dimensionResource(R.dimen.email_header_content_padding_vertical)
                ),
            verticalArrangement = Arrangement.Center
        ) {
            val (firstName, _) = email.senderFullName.split(" ")

            Text(
                text = firstName,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
            )
            Text(
                text = email.createdAt,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.outline,
            )
        }
    }
}

@Composable
fun SenderAccountProfileImage(
    @DrawableRes drawableResource: Int,
    contentDescription: String,
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(drawableResource),
        contentDescription = contentDescription,
        modifier = modifier
            .size(dimensionResource(R.dimen.email_header_profile_size))
            .clip(CircleShape),
    )
}

@Composable
private fun EmailItemBody(email: EmailUiState) {
    Text(
        text = email.subject ?: stringResource(id = R.string.email_no_subject),
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onSurface,
        fontStyle = if (email.subject == null) FontStyle.Italic else null,
        modifier = Modifier.padding(
            top = dimensionResource(R.dimen.email_list_item_header_subject_spacing),
            bottom = dimensionResource(R.dimen.email_list_item_subject_body_spacing),
        ),
    )
    Text(
        text = email.body,
        style = MaterialTheme.typography.bodyMedium,
        maxLines = 2,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        overflow = TextOverflow.Ellipsis,
    )
}

private fun getNavigationComposableType(windowSize: WindowWidthSizeClass) = when (windowSize) {
    WindowWidthSizeClass.Compact -> NavigationComposableType.BottomNavigationBar
    WindowWidthSizeClass.Medium -> NavigationComposableType.NavigationRail
    WindowWidthSizeClass.Expanded -> NavigationComposableType.PermanentNavigationDrawer
    else -> NavigationComposableType.BottomNavigationBar
}

@Preview
@Composable
fun EmailsScreenPreview(modifier: Modifier = Modifier) {
    AppTheme {
        EmailsScreen()
    }
}
