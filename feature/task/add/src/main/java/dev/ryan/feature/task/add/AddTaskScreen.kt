package dev.ryan.feature.task.add

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import dev.ryan.core.ui.DevicePreviews

@Composable
internal fun AddTaskRoute(
    modifier: Modifier = Modifier,
    viewModel: AddTaskViewModel = hiltViewModel(),
    onNavigateToHome: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {},
) {
    val addTaskUiState by viewModel.uiState.collectAsState()
    AddTaskScreen(
        addTaskUiState = addTaskUiState,
        modifier = modifier,
        onTitleChanged = {viewModel.onTitleChanged(title = it)},
        onDescriptionChanged = {viewModel.onDescriptionChanged(desc = it)},
        onSaveTask = {viewModel.onSaveTask()}
    )
}


@Composable
fun AddTaskScreen(
    addTaskUiState: AddTaskUiState,
    modifier: Modifier,
    onTitleChanged: (String) -> Unit = {},
    onDescriptionChanged: (String) -> Unit = {},
    onSaveTask: () -> Unit = {},
) {
    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {
        OutlinedTextField(
            value = addTaskUiState.title,
            onValueChange = { onTitleChanged(it) },
            label = { Text("Task Title") }
        )

        OutlinedTextField(
            value = addTaskUiState.description,
            onValueChange = { onDescriptionChanged(it) },
            label = { Text("Description") }
        )

        Spacer(modifier.height(16.dp))
        Button(onClick = { onSaveTask }) {
            Text("Save Task")
        }
    }
}

@DevicePreviews
@Composable
fun AddTaskScreenPreview() {
    AddTaskScreen(
        addTaskUiState = AddTaskUiState(),
        modifier = Modifier
    )
}

