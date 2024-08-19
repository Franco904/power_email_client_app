package com.example.power_email_client.emailDetails.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.power_email_client.core.presentation.theme.AppTheme

@Composable
fun EmailDetailsScreen(
    modifier: Modifier = Modifier,
) {
    Text(text = "Email Details", modifier)
}

@Preview
@Composable
fun EmailDetailsPreview(modifier: Modifier = Modifier) {
    AppTheme {
        EmailDetailsScreen()
    }
}
