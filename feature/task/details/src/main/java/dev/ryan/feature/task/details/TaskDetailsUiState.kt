package dev.ryan.feature.task.details

import dev.ryan.core.domain.model.Task

data class TaskDetailsUiState(
    val task: Task? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
