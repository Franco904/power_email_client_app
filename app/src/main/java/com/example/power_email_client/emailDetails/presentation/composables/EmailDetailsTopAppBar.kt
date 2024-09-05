package com.example.power_email_client.emailDetails.presentation.composables

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.power_email_client.R
import com.example.power_email_client.emailDetails.presentation.models.EmailDetailsUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailDetailsTopAppBar(
    email: EmailDetailsUiState?,
    modifier: Modifier = Modifier,
    onUpNavigation: () -> Boolean,
) {
    TopAppBar(
        title = {
            TopAppBarTitle(email)
        },
        navigationIcon = {
            TopAppBarNavigationIcon(onUpNavigation)
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
        modifier = modifier
            .padding(12.dp)
    )
}

@Composable
private fun TopAppBarTitle(email: EmailDetailsUiState?) {
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
        modifier = Modifier
            .padding(start = 8.dp)
    )
}

@Composable
private fun TopAppBarNavigationIcon(onUpNavigation: () -> Boolean) {
    IconButton(
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = MaterialTheme.colorScheme.onPrimary,
//            disabledContainerColor = MaterialTheme.colorScheme.onBackground,
        ),
        onClick = {
            onUpNavigation().takeIf { wasSucceeded -> !wasSucceeded }?.run {
                Log.e("EmailDetailsScreen", "Navigation up failed!")
            }
        }
    ) {
        Icon(
            Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
        )
    }
}
