package dev.ryan.core.data

import dev.ryan.core.data.local.toEntity
import dev.ryan.core.data.repository.TaskRepositoryImpl
import dev.ryan.core.database.dao.TaskDao
import dev.ryan.core.domain.model.Task
import dev.ryan.core.domain.model.TaskStatus
import io.mockk.MockKAnnotations
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class TaskRepositoryImplTest {

    @MockK(relaxed = true)
    lateinit var taskDao: TaskDao

    private lateinit var taskRepositoryImpl: TaskRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        taskRepositoryImpl = TaskRepositoryImpl(taskDao)
    }

    @Test
    fun `getTasksByDate should handled normally`() = runTest {
        val date = 12345L
        coEvery { taskDao.getTasksByDate(date) } returns flowOf(emptyList())
        val result = taskRepositoryImpl.getTasksByDate(date).first()
        assert(result.isEmpty())
    }

    @Test
    fun `insertTask should handled normally`() = runTest {
        val task = mockk<Task>(relaxed = true)
        coEvery { taskDao.insertTask(task = task.toEntity()) } just Runs
        taskRepositoryImpl.insertTask(task)
        coVerify(exactly = 1) { taskDao.insertTask(task = task.toEntity()) }
    }

    @Test
    fun `updateTask should handled normally`() = runTest {
        val task = mockk<Task>(relaxed = true)
        coEvery { taskDao.updateTask(task = task.toEntity()) } just Runs
        taskRepositoryImpl.updateTask(task = task)
        coVerify(exactly = 1) { taskDao.updateTask(task = task.toEntity()) }
    }

    @Test
    fun `updateTaskStatus should handled normally`() = runTest {
        val id = 1
        val taskStatus: TaskStatus = TaskStatus.TODO
        coEvery { taskDao.updateTaskStatus(id = id, status = taskStatus.name) } just Runs
        taskRepositoryImpl.updateTaskStatus(id = id, status = taskStatus)
        coVerify(exactly = 1) { taskDao.updateTaskStatus(id = id, status = taskStatus.name) }
    }

    @Test
    fun `deleteTaskById should handled normally`() = runTest {
        val id = 1
        coEvery { taskDao.deleteTaskById(id = id) } just Runs
        taskRepositoryImpl.deleteTaskById(id = id)
        coVerify(exactly = 1) { taskDao.deleteTaskById(id = id) }
    }
}