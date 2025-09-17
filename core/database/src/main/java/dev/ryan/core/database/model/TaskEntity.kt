package dev.ryan.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String?,
    val type: String,
    val date: Long,
    val time: Long,
    val status: String = "TODO"
)