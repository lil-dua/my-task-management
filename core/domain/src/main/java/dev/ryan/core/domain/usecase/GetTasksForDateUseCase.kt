package dev.ryan.core.domain.usecase

import dev.ryan.core.domain.model.Task
import dev.ryan.core.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class GetTasksForDateUseCase(
    private val repository: TaskRepository
) {
    operator fun invoke(date: Long): Flow<List<Task>> {
        return repository.getTasksByDate(date)
    }
}