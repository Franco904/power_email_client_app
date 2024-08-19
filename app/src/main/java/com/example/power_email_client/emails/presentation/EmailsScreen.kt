package com.example.power_email_client.emails.presentation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.power_email_client.R
import com.example.power_email_client.core.domain.models.MailboxType
import com.example.power_email_client.core.presentation.theme.AppTheme
import com.example.power_email_client.emails.presentation.models.EmailUiState
import com.example.power_email_client.emails.presentation.models.NavigationBarItem

@Composable
fun EmailsScreen(
    modifier: Modifier = Modifier,
    viewModel: EmailsViewModel = viewModel(),
    onEmailSelected: (Long) -> Unit = {},
) {
    val emails by viewModel.currentEmails.collectAsStateWithLifecycle()
    val mailboxType by viewModel.currentMailboxType.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            EmailsTopAppBar()
        },
        bottomBar = {
            EmailsBottomNavigationBar(
                currentTabMailboxType = mailboxType,
                onBarItemSelected = viewModel::onBarItemSelected,
            )
        },
        modifier = modifier
    ) { innerPadding ->
        EmailsList(
            emails = emails,
            onEmailSelected = onEmailSelected,
            modifier = Modifier
                .padding(
                    top = innerPadding.calculateTopPadding(),
                    bottom = innerPadding.calculateBottomPadding(),
                )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailsTopAppBar(
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.reply).uppercase(),
                style = MaterialTheme.typography.bodyLarge.copy(
                    letterSpacing = 5.sp,
                ),
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .padding(start = 4.dp)
            )
        },
        actions = {
            Image(
                painter = painterResource(id = R.drawable.avatar_2),
                contentDescription = stringResource(id = R.string.profile),
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
        modifier = modifier
            .padding(
                top = 12.dp,
                end = 12.dp,
                bottom = 12.dp,
            )
    )
}

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
        for (navItem in NavigationBarItem.entries) {
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

@Composable
fun EmailsList(
    emails: List<EmailUiState>,
    onEmailSelected: (Long) -> Unit,
    modifier: Modifier = Modifier,
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
    onEmailSelected: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
        ),
        // TODO: Build details screen for small screen sizes (phones)
        onClick = { onEmailSelected(email.id) },
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
private fun EmailItemBody(email: EmailUiState) {
    Text(
        text = email.subject ?: stringResource(id = R.string.email_no_subject),
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onSurface,
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
                style = MaterialTheme.typography.labelMedium
            )
            Text(
                text = email.createdAt,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.outline
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

@Preview
@Composable
fun EmailsScreenPreview(modifier: Modifier = Modifier) {
    AppTheme {
        EmailsScreen()
    }
}
