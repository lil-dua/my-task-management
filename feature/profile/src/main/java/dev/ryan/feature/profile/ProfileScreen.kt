package dev.ryan.feature.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import dev.ryan.core.ui.DevicePreviews

@Composable
internal fun ProfileRoute(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = hiltViewModel(),
    onNavigateToHome: () -> Unit = {},
    onNavigateToAddTask: () -> Unit = {},
) {
    val profileState by viewModel.uiState.collectAsState()
    ProfileScreen(
        profileState = profileState,
        modifier = modifier,
        onToggleNotifications = { viewModel.toggleNotifications() },
        onToggleDarkTheme = { viewModel.toggleDarkTheme() }
    )

}
@Composable
fun ProfileScreen(
    profileState: ProfileUiState,
    modifier: Modifier,
    onToggleNotifications: () -> Unit = {},
    onToggleDarkTheme: () -> Unit = {},
) {

    Column(modifier.fillMaxSize().padding(16.dp)) {
        Row(
            modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Enable Notifications")
            Switch(
                checked = profileState.notificationsEnabled,
                onCheckedChange = { onToggleNotifications }
            )
        }

        Row(
            modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Dark Theme")
            Switch(
                checked = profileState.darkThemeEnabled,
                onCheckedChange = { onToggleDarkTheme }
            )
        }
    }
}

@DevicePreviews
@Composable
fun SettingsScreenPreview() {
    ProfileScreen(
        profileState = ProfileUiState(),
        modifier = Modifier
    )
}

