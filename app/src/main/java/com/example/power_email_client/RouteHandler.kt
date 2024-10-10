package com.example.power_email_client

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.power_email_client.core.presentation.utils.alsoInvoke
import com.example.power_email_client.core.presentation.utils.getEmailIdFromDetails
import com.example.power_email_client.core.presentation.utils.setEmailIdFromDetails
import com.example.power_email_client.emailDetails.presentation.EmailDetailsScreen
import com.example.power_email_client.emailDetails.presentation.EmailDetailsViewModel
import com.example.power_email_client.emails.presentation.EmailsScreen
import com.example.power_email_client.emails.presentation.EmailsViewModel

object RouteHandler {
    fun NavGraphBuilder.routes(
        navController: NavController,
        windowSize: WindowSizeClass,
    ) {
        composable<Routes.Emails> { backStackEntry ->
            val selectedEmailId = backStackEntry.getEmailIdFromDetails()

            EmailsScreen(
                viewModel = viewModel<EmailsViewModel>().alsoInvoke { init(selectedEmailId) },
                onEmailSelectedToDetailsScreen = { emailId ->
                    navController.navigate(Routes.EmailDetails(emailId))
                },
                windowSize = windowSize.widthSizeClass,
            )
        }
        composable<Routes.EmailDetails> { backStackEntry ->
            val emailId = (backStackEntry.toRoute() as Routes.EmailDetails).emailId

            EmailDetailsScreen(
                viewModel = viewModel<EmailDetailsViewModel>().alsoInvoke { init(emailId) },
                onNavigateUp = {
                    navController.previousBackStackEntry?.setEmailIdFromDetails(null)
                    navController.navigateUp()
                },
                onWindowSizeLayoutChanged = {
                    navController.previousBackStackEntry?.setEmailIdFromDetails(emailId)
                    navController.navigateUp()
                },
                windowSize = windowSize.widthSizeClass,
            )
        }
    }
}
