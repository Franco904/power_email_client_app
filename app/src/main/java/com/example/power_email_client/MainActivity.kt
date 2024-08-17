package com.example.power_email_client

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.power_email_client.core.presentation.theme.AppTheme
import com.example.power_email_client.emailDetails.EmailDetailsScreen
import com.example.power_email_client.emails.EmailsScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Routes.Emails,
                    builder = { routes(navController) },
                )
            }
        }
    }

    private fun NavGraphBuilder.routes(navController: NavController) {
        composable<Routes.Emails> {
            EmailsScreen()
        }
        composable<Routes.EmailDetails> {
            EmailDetailsScreen()
        }
    }
}
