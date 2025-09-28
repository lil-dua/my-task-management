package dev.ryan.core.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.ryan.core.domain.repository.TaskRepository
import dev.ryan.core.domain.usecase.CreateTaskUseCase
import dev.ryan.core.domain.usecase.DeleteTaskUseCase
import dev.ryan.core.domain.usecase.GetTaskStatsForDateUseCase
import dev.ryan.core.domain.usecase.GetTasksForDateUseCase
import dev.ryan.core.domain.usecase.UpdateTaskStatusUseCase
import dev.ryan.core.domain.usecase.UpdateTaskUseCase

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    fun provideCreateTaskUseCase(repo: TaskRepository) = CreateTaskUseCase(repo)

    @Provides
    fun provideDeleteTaskUseCase(repo: TaskRepository) = DeleteTaskUseCase(repo)

    @Provides
    fun provideGetTasksByDateUseCase(repo: TaskRepository) = GetTasksForDateUseCase(repo)

    @Provides
    fun provideGetTasksStatsForDateUseCase(repo: TaskRepository) = GetTaskStatsForDateUseCase(repo)

    @Provides
    fun provideUpdateTaskStatusUseCase(repo: TaskRepository) = UpdateTaskStatusUseCase(repo)

    @Provides
    fun provideUpdateTaskUseCase(repo: TaskRepository) = UpdateTaskUseCase(repo)

}
