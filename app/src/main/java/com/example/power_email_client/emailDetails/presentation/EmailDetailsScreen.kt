package com.example.power_email_client.emailDetails.presentation

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MarkunreadMailbox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.power_email_client.R
import com.example.power_email_client.core.presentation.theme.AppTheme
import com.example.power_email_client.emailDetails.presentation.composables.EmailDetailsTopAppBar
import com.example.power_email_client.emailDetails.presentation.models.EmailDetailsUiState

@Composable
fun EmailDetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: EmailDetailsViewModel = viewModel(),
    onUpNavigation: () -> Boolean = { true },
) {
    val email by viewModel.email.collectAsStateWithLifecycle()

    BackHandler { onUpNavigation() }

    Scaffold(
        topBar = {
            EmailDetailsTopAppBar(
                email = email,
                onUpNavigation = onUpNavigation,
            )
        },
        modifier = modifier
    ) { innerPadding ->
        EmailDetailsCard(
            email = email,
            modifier = Modifier
                .padding(top = innerPadding.calculateTopPadding())
        )
    }
}

@Composable
fun EmailDetailsCard(
    email: EmailDetailsUiState?,
    modifier: Modifier = Modifier,
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(MaterialTheme.colorScheme.onBackground)
            .verticalScroll(rememberScrollState())
    ) {
        if (email == null) NoEmailSelectedContent() else EmailDetailsContent(email)
    }
}

@Composable
private fun NoEmailSelectedContent(
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .padding(
                top = 64.dp,
                bottom = 64.dp,
            )
    ) {
        Icon(
            imageVector = Icons.Default.MarkunreadMailbox,
            contentDescription = stringResource(R.string.no_email_selected),
            tint = MaterialTheme.colorScheme.outline,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(R.string.no_email_selected),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Composable
private fun EmailDetailsContent(email: EmailDetailsUiState) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {
        SenderAccountDetails(
            senderPictureId = email.senderPictureId,
            senderFullName = email.senderFullName,
            createdAtText = email.createdAt,
        )
        Spacer(modifier = Modifier.height(24.dp))
        EmailSubjectText(subject = email.subject)
        Spacer(modifier = Modifier.height(16.dp))
        EmailBodyText(body = email.body)
        Spacer(modifier = Modifier.height(24.dp))
        ReplyButton()
    }
}

@Composable
private fun SenderAccountDetails(
    @DrawableRes senderPictureId: Int,
    senderFullName: String,
    createdAtText: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = senderPictureId),
            contentDescription = senderFullName,
            modifier = Modifier
                .size(dimensionResource(R.dimen.email_header_profile_size))
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = senderFullName, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(1.dp))
            Text(text = createdAtText)
        }
    }
}

@Composable
private fun EmailSubjectText(
    subject: String?,
    modifier: Modifier = Modifier,
) {
    Text(
        text = subject ?: stringResource(id = R.string.email_no_subject),
        style = MaterialTheme.typography.bodyLarge,
        fontWeight = FontWeight.Bold,
        fontStyle = if (subject == null) FontStyle.Italic else null,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier
    )
}

@Composable
private fun EmailBodyText(
    body: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = body,
        style = MaterialTheme.typography.bodyLarge,
        modifier = modifier
    )
}

@Composable
private fun ReplyButton(
    modifier: Modifier = Modifier,
) {
    Button(
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
        onClick = { /*TODO*/ },
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.reply),
            color = MaterialTheme.colorScheme.onPrimaryContainer,
        )
    }
}


@Preview
@Composable
fun EmailDetailsPreview(modifier: Modifier = Modifier) {
    AppTheme {
        EmailDetailsScreen()
    }
}
