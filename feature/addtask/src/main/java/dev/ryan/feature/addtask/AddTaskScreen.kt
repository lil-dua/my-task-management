package dev.ryan.feature.addtask

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
fun AddTaskScreen(
    viewModel: AddTaskViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        OutlinedTextField(
            value = state.title,
            onValueChange = { viewModel.onTitleChanged(it) },
            label = { Text("Task Title") }
        )

        OutlinedTextField(
            value = state.description,
            onValueChange = { viewModel.onDescriptionChanged(it) },
            label = { Text("Description") }
        )

        Spacer(Modifier.height(16.dp))
        Button(onClick = { viewModel.onSaveTask() }) {
            Text("Save Task")
        }
    }
}

@DevicePreviews
@Composable
fun AddTaskScreenPreview() {
    AddTaskScreen()
}

