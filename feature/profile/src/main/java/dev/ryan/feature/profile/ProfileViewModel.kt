package dev.ryan.feature.profile

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    fun toggleNotifications() {
        _uiState.update { it.copy(notificationsEnabled = !it.notificationsEnabled) }
    }

    fun toggleDarkTheme() {
        _uiState.update { it.copy(darkThemeEnabled = !it.darkThemeEnabled) }
    }
}
