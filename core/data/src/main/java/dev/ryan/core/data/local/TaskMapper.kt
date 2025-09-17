package dev.ryan.core.data.local

import dev.ryan.core.database.model.TaskEntity
import dev.ryan.core.domain.model.Task
import dev.ryan.core.domain.model.TaskStatus

fun TaskEntity.toDomain(): Task {
    return Task(
        id = id,
        title = title,
        description = description,
        type = type,
        date = date,
        time = time,
        status = when (status) {
            "TODO" -> TaskStatus.TODO
            "IN_PROGRESS" -> TaskStatus.IN_PROGRESS
            "DONE" -> TaskStatus.DONE
            else -> TaskStatus.TODO
        }
    )
}

fun Task.toEntity(): TaskEntity {
    return TaskEntity(
        id = id,
        title = title,
        description = description,
        type = type,
        date = date,
        time = time,
        status = status.name
    )
}