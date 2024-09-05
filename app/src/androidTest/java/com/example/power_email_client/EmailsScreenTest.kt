package com.example.power_email_client

import androidx.activity.ComponentActivity
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.power_email_client.annotations.CompactWidthTest
import com.example.power_email_client.annotations.ExpandedWidthTest
import com.example.power_email_client.annotations.MediumWidthTest
import com.example.power_email_client.core.presentation.theme.AppTheme
import com.example.power_email_client.emails.presentation.EmailsScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EmailsScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    @CompactWidthTest
    fun shouldDisplayCorrectNavigationLayoutOnCompatWidth(): Unit = with(composeTestRule) {
        setContent {
            AppTheme {
                EmailsScreen(windowSize = WindowWidthSizeClass.Compact)
            }
        }

        onNodeWithTag(context.getString(R.string.top_app_bar)).assertIsDisplayed()
        onNodeWithTag(context.getString(R.string.navigation_bottom)).assertIsDisplayed()

        onNodeWithTag(context.getString(R.string.navigation_rail)).assertDoesNotExist()
        onNodeWithTag(context.getString(R.string.navigation_drawer)).assertDoesNotExist()
    }

    @Test
    @MediumWidthTest
    fun shouldDisplayCorrectNavigationLayoutOnMediumWidth(): Unit = with(composeTestRule) {
        setContent {
            AppTheme {
                EmailsScreen(windowSize = WindowWidthSizeClass.Medium)
            }
        }

        onNodeWithTag(context.getString(R.string.top_app_bar)).assertIsDisplayed()
        onNodeWithTag(context.getString(R.string.navigation_rail)).assertIsDisplayed()

        onNodeWithTag(context.getString(R.string.navigation_bottom)).assertDoesNotExist()
        onNodeWithTag(context.getString(R.string.navigation_drawer)).assertDoesNotExist()
    }

    @Test
    @ExpandedWidthTest
    fun shouldDisplayCorrectNavigationLayoutExpandedWidth(): Unit = with(composeTestRule) {
        setContent {
            AppTheme {
                EmailsScreen(windowSize = WindowWidthSizeClass.Expanded)
            }
        }

        onNodeWithTag(context.getString(R.string.navigation_drawer)).assertIsDisplayed()

        onNodeWithTag(context.getString(R.string.top_app_bar)).assertDoesNotExist()
        onNodeWithTag(context.getString(R.string.navigation_bottom)).assertDoesNotExist()
        onNodeWithTag(context.getString(R.string.navigation_rail)).assertDoesNotExist()
    }
}
