package com.example.power_email_client.core.presentation.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.ViewModel

@Composable
fun <VM : ViewModel> VM.alsoInvoke(onViewModel: VM.() -> Unit) = also {
    LaunchedEffect(Unit) { onViewModel() }
}
