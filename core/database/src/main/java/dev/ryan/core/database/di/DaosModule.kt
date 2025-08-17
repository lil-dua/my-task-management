package dev.ryan.core.database.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.ryan.core.database.MtmDatabase
import dev.ryan.core.database.dao.TaskDao

@Module
@InstallIn(SingletonComponent::class)
internal object DaosModule {

    @Provides
    fun providesTopicsDao(
        database: MtmDatabase,
    ): TaskDao = database.taskDao()

}