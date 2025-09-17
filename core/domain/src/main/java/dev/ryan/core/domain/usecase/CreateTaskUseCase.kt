package dev.ryan.core.domain.usecase

import dev.ryan.core.domain.model.Task
import dev.ryan.core.domain.repository.TaskRepository

class CreateTaskUseCase(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(task: Task) {
        repository.insertTask(task)
    }
}