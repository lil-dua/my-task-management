package dev.ryan.core.data

import dev.ryan.core.data.local.toDomain
import dev.ryan.core.data.local.toEntity
import dev.ryan.core.database.model.TaskEntity
import dev.ryan.core.domain.model.Task
import dev.ryan.core.domain.model.TaskStatus
import org.junit.Test

class TaskMapperTest {

    @Test
    fun `TaskEntity toDomain should map all status branches`() {
        val cases = listOf(
            "TODO" to TaskStatus.TODO,
            "IN_PROGRESS" to TaskStatus.IN_PROGRESS,
            "DONE" to TaskStatus.DONE,
            "UNKNOWN" to TaskStatus.TODO,
        )

        cases.forEach { (input, expected) ->
            val entity = TaskEntity(
                id = 1,
                title = "Task",
                description = "Desc",
                type = "Type",
                date = 1L,
                time = 1L,
                status = input
            )

            val domain = entity.toDomain()

            assert(domain.status == expected)
        }
    }

    @Test
    fun `Task should be converted to entity model`() {
        val task = Task(
            id = 1,
            title = "Task 1",
            description = "Description 1",
            type = "Type 1",
            date = 1234567890L,
            time = 987654321L,
            status = TaskStatus.TODO
        )
        val entity = task.toEntity()
        assert(entity.id == task.toEntity().id)
    }
}