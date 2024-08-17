package com.example.power_email_client.emails

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.power_email_client.core.presentation.theme.AppTheme

@Composable
fun EmailsScreen(
    modifier: Modifier = Modifier,
) {
    Text(text = "Emails", modifier)
}

@Preview
@Composable
fun EmailsScreenPreview(modifier: Modifier = Modifier) {
    AppTheme {
        EmailsScreen()
    }
}
