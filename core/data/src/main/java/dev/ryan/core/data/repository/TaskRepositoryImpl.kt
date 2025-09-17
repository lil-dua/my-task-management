package dev.ryan.core.data.repository

import dev.ryan.core.data.local.toDomain
import dev.ryan.core.data.local.toEntity
import dev.ryan.core.database.dao.TaskDao
import dev.ryan.core.domain.model.Task
import dev.ryan.core.domain.model.TaskStatus
import dev.ryan.core.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.collections.map

class TaskRepositoryImpl(
    private val taskDao: TaskDao
): TaskRepository {
    override fun getTasksByDate(date: Long): Flow<List<Task>> {
        return taskDao.getTasksByDate(date).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun insertTask(task: Task) {
        taskDao.insertTask(task.toEntity())
    }

    override suspend fun updateTask(task: Task) {
        taskDao.updateTask(task.toEntity())
    }

    override suspend fun updateTaskStatus(id: Int, status: TaskStatus) {
        taskDao.updateTaskStatus(id, status.name)
    }

    override suspend fun deleteTaskById(id: Int) {
        taskDao.deleteTaskById(id)
    }
}