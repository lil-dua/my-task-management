package dev.ryan.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import dev.ryan.core.designsystem.theme.MtmTheme
import dev.ryan.core.ui.DevicePreviews
import dev.ryan.feature.home.navigation.HomeRoute

@Composable
internal fun HomeRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigateToAddTask: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {},
) {
    val homeState by viewModel.uiState.collectAsState()
    HomeScreen(
        homeState = homeState,
        modifier = modifier
    )
}

@Composable
fun HomeScreen(
    homeState: HomeUiState,
    modifier: Modifier = Modifier
) {

    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {
        Text("Hello, ${homeState.userName}", style = MaterialTheme.typography.headlineSmall)
        Text("Today's progress: ${homeState.completedPercent}%")

        Spacer(modifier.height(16.dp))
        homeState.taskTypes.forEach { (type, count) ->
            Text("$type: $count tasks")
        }
    }
}

@DevicePreviews
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        homeState = HomeUiState()
    )
}
