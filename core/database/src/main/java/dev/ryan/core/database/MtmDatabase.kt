package dev.ryan.core.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import dev.ryan.core.database.dao.TaskDao
import dev.ryan.core.database.model.TaskEntity

@Database(
    entities = [
        TaskEntity::class
    ],
    version = 1,
)
internal abstract class MtmDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}