package com.example.power_email_client

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.power_email_client.RouteHandler.routes
import com.example.power_email_client.core.presentation.theme.AppTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                val windowSize = calculateWindowSizeClass(activity = this)
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Routes.Emails,
                    builder = { routes(navController, windowSize) },
                )
            }
        }
    }
}
