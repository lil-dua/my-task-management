package dev.ryan.core.domain.usecase

import dev.ryan.core.domain.model.TaskStatus
import dev.ryan.core.domain.repository.TaskRepository

class UpdateTaskUseCase(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(id: Int, status: TaskStatus) {
        repository.updateTaskStatus(id, status)
    }
}