package dev.ryan.feature.task.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import dev.ryan.core.domain.model.Task
import dev.ryan.core.domain.model.TaskStatus
import dev.ryan.core.ui.DevicePreviews

@Composable
fun TaskDetailScreen(
    viewModel: TaskDetailViewModel = hiltViewModel(),
    task: Task,
    onTaskDeleted: () -> Unit
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(task) {
        viewModel.loadTask(task)
    }

    state.task?.let {
        Column(Modifier.fillMaxSize().padding(16.dp)) {
            Text("Title: ${it.title}", style = MaterialTheme.typography.titleLarge)
            Text("Description: ${it.description ?: "No description"}")
            Text("Type: ${it.type}")
            Text("Status: ${it.status}")

            Spacer(Modifier.height(16.dp))
            Row {
                Button(onClick = { viewModel.updateStatus(TaskStatus.IN_PROGRESS) }) {
                    Text("In Progress")
                }
                Spacer(Modifier.width(8.dp))
                Button(onClick = { viewModel.updateStatus(TaskStatus.DONE) }) {
                    Text("Mark Done")
                }
            }

            Spacer(Modifier.height(16.dp))
            Button(onClick = {
                viewModel.deleteTask()
                onTaskDeleted()
            }) {
                Text("Delete Task", color = Color.White)
            }
        }
    }
}

@DevicePreviews
@Composable
fun TaskDetailScreenPreview() {
    TaskDetailScreen(
        task = Task(
            id = 1,
            title = "Task 1",
            description = "Description 1",
            type = "Type 1",
            date = 20250312,
            time = 12,
            status = TaskStatus.IN_PROGRESS
        ),
        onTaskDeleted = {}
    )
}
