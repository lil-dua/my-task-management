package dev.ryan.core.domain.model

data class Task(
    val id: Int = 0,
    val title: String,
    val description: String? = null,
    val type: String,
    val date: Long,
    val time: Long,
    val status: TaskStatus = TaskStatus.TODO
)

enum class TaskStatus {
    TODO,
    IN_PROGRESS,
    DONE
}