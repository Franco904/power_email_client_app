package com.example.power_email_client.emailDetails.presentation

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
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
        Card(
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    top = innerPadding.calculateTopPadding(),
                    end = 16.dp,
                )
        ) {
            if (email != null) {

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
            Text(
                if (email == null) "" else email.subject
                    ?: stringResource(id = R.string.email_no_subject),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Medium,
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
