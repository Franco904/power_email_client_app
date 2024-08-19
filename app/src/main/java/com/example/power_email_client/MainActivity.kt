package com.example.power_email_client

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.power_email_client.core.presentation.theme.AppTheme
import com.example.power_email_client.emailDetails.presentation.EmailDetailsScreen
import com.example.power_email_client.emailDetails.presentation.EmailDetailsViewModel
import com.example.power_email_client.emails.presentation.EmailsScreen
import com.example.power_email_client.emails.presentation.EmailsViewModel

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
            EmailsScreen(
                viewModel = viewModel<EmailsViewModel>().apply { init() },
                onEmailSelected = { emailId ->
                    navController.navigate(Routes.EmailDetails(emailId))
                }
            )
        }
        composable<Routes.EmailDetails> { backstackEntry ->
            val emailId = (backstackEntry.toRoute() as Routes.EmailDetails).emailId

            EmailDetailsScreen(
                viewModel = viewModel<EmailDetailsViewModel>().apply { init(emailId) },
                onUpNavigation = navController::navigateUp,
            )
        }
    }
}
