package dev.ryan.core.domain.usecase

import dev.ryan.core.domain.model.TaskStatus
import dev.ryan.core.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetTaskStatsForDateUseCase(
    private val repository: TaskRepository
) {
    operator fun invoke(date: Long): Flow<TaskStats> {
        return repository.getTasksByDate(date).map { tasks ->
            val total = tasks.size
            val done = tasks.count { it.status == TaskStatus.DONE }
            val percent = if (total > 0) (done * 100) / total else 0
            TaskStats(total, done, percent)
        }
    }
}

data class TaskStats(
    val total: Int,
    val completed: Int,
    val percent: Int
)