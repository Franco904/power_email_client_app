package com.example.power_email_client.emailDetails.presentation

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.colorspace.ColorSpaces
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
import com.example.power_email_client.emailDetails.presentation.models.EmailDetailsUiState

@Composable
fun EmailDetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: EmailDetailsViewModel = viewModel(),
    onUpNavigation: () -> Boolean = { true },
) {
    val email by viewModel.email.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            EmailDetailsTopAppBar(
                email = email,
                onUpNavigation = onUpNavigation,
            )
        },
        modifier = modifier
    ) { innerPadding ->
        Column {
            Surface(
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .padding(
                        start = 16.dp,
                        top = innerPadding.calculateTopPadding(),
                        end = 16.dp,
                    )
                    .background(MaterialTheme.colorScheme.onBackground)
                    .verticalScroll(rememberScrollState())
            ) {
                if (email == null) {
                    Text(
                        text = stringResource(R.string.no_email_found_try_opening_another_one),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                top = 64.dp,
                                bottom = 64.dp,
                            )
                    )
                } else {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp)
                    ) {
                        Row {
                            Image(
                                painter = painterResource(id = email!!.senderPictureId),
                                contentDescription = email!!.senderFullName,
                                modifier = Modifier
                                    .size(dimensionResource(R.dimen.email_header_profile_size))
                                    .clip(CircleShape)
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Column {
                                Text(text = email!!.senderFullName, fontWeight = FontWeight.Bold)
                                Spacer(modifier = Modifier.height(1.dp))
                                Text(text = email!!.createdAt)
                            }
                        }
                        Spacer(modifier = Modifier.height(24.dp))
                        Text(
                            text = email!!.subject
                                ?: stringResource(id = R.string.email_no_subject),
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold,
                            fontStyle = if (email!!.subject == null) FontStyle.Italic else null,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = email!!.body,
                            style = MaterialTheme.typography.bodyLarge,
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        Button(
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                            ),
                            onClick = { /*TODO*/ },
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = stringResource(id = R.string.reply),
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailDetailsTopAppBar(
    email: EmailDetailsUiState?,
    onUpNavigation: () -> Boolean,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = {
            val title = if (email == null) {
                stringResource(id = R.string.email_absent)
            } else {
                email.subject ?: stringResource(id = R.string.email_no_subject)
            }

            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Medium,
                fontStyle = if (email?.subject == null) FontStyle.Italic else null,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(start = 8.dp)
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                onUpNavigation().takeIf { wasSucceeded -> !wasSucceeded }?.run {
                    Log.e("EmailDetailsScreen", "Navigation up failed!")
                }
            }) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                )
            }
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

@Preview
@Composable
fun EmailDetailsPreview(modifier: Modifier = Modifier) {
    AppTheme {
        EmailDetailsScreen()
    }
}
