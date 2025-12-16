package dev.ryan.feature.task.tasklist

import dev.ryan.core.domain.model.Task

data class TaskListUiState(
    val listTask: List<Task> = emptyList(),
)