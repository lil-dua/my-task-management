package dev.ryan.core.data.local

import dev.ryan.core.database.model.TaskEntity
import dev.ryan.core.domain.model.Task
import dev.ryan.core.domain.model.TaskStatus

fun TaskEntity.toDomain(): Task {
    val taskStatus = runCatching {
        TaskStatus.valueOf(status)
    }.getOrDefault(TaskStatus.TODO)

    return Task(
        id = id,
        title = title,
        description = description,
        type = type,
        date = date,
        time = time,
        status = taskStatus
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