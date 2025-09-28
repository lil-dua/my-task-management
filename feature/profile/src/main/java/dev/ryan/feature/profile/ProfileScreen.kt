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
fun SettingsScreen(
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Enable Notifications")
            Switch(
                checked = state.notificationsEnabled,
                onCheckedChange = { viewModel.toggleNotifications() }
            )
        }

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Dark Theme")
            Switch(
                checked = state.darkThemeEnabled,
                onCheckedChange = { viewModel.toggleDarkTheme() }
            )
        }
    }
}

@DevicePreviews
@Composable
fun SettingsScreenPreview() {
    SettingsScreen()
}

