package dev.ryan.feature.settings

data class SettingsUiState(
    val notificationsEnabled: Boolean = true,
    val darkThemeEnabled: Boolean = false
)