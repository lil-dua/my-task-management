package dev.ryan.feature.task.tasklist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import dev.ryan.core.ui.DevicePreviews

@Composable
internal fun TaskListRoute(
    modifier: Modifier = Modifier,
    viewModel: TaskListViewModel = hiltViewModel(),
    onNavigateToHome: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {},
) {
    val profileState by viewModel.uiState.collectAsState()
    TaskListScreen(
        profileState = profileState,
        modifier = modifier,
    )

}
@Composable
fun TaskListScreen(
    profileState: TaskListUiState,
    modifier: Modifier
) {

    Column(modifier.fillMaxSize().padding(16.dp)) {
        Row(
            modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text("List Task Screen")
        }
    }
}

@DevicePreviews
@Composable
fun TaskListScreenPreview() {
    TaskListScreen(
        profileState = TaskListUiState(),
        modifier = Modifier
    )
}

