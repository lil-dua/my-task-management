package dev.ryan.core.domain.repository

import dev.ryan.core.domain.model.Task
import dev.ryan.core.domain.model.TaskStatus
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    fun getTasksByDate(date: Long): Flow<List<Task>>

    suspend fun insertTask(task: Task)

    suspend fun updateTask(task: Task)

    suspend fun updateTaskStatus(id: Int, status: TaskStatus)

    suspend fun deleteTaskById(id: Int)
}