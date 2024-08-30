package com.example.power_email_client.emails.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.power_email_client.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailsTopAppBar(
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = { ReplyTitle() },
        actions = { AccountRoundedPicture() },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
        modifier = modifier
            .fillMaxWidth()
            .shadow(elevation = 4.dp)
    )
}

@Composable
fun ReplyTitle(
    modifier: Modifier = Modifier,
) {
    Text(
        text = stringResource(id = R.string.reply).uppercase(),
        style = MaterialTheme.typography.bodyLarge.copy(
            letterSpacing = 5.sp,
        ),
        color = MaterialTheme.colorScheme.primary,
        fontWeight = FontWeight.Medium,
        modifier = modifier
            .padding(vertical = 12.dp)
    )
}

@Composable
fun AccountRoundedPicture(
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = R.drawable.avatar_2),
        contentDescription = stringResource(id = R.string.profile),
        modifier = modifier
            .padding(vertical = 12.dp)
            .padding(end = 12.dp)
            .size(48.dp)
            .clip(CircleShape)
    )
}
