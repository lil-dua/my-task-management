package dev.ryan.core.domain.usecase

import dev.ryan.core.domain.repository.TaskRepository

class DeleteTaskUseCase(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(id: Int) {
        repository.deleteTaskById(id)
    }
}