package dev.ryan.mytaskmanagement.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.ryan.core.data.repository.TaskRepositoryImpl
import dev.ryan.core.database.dao.TaskDao
import dev.ryan.core.domain.repository.TaskRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Repository
    @Provides
    @Singleton
    fun provideTaskRepository(dao: TaskDao): TaskRepository =
        TaskRepositoryImpl(dao)

    // GoogleAuthUiClient (Google Sign-In helper)
//    @Provides
//    @Singleton
//    fun provideGoogleAuthUiClient(@ApplicationContext ctx: Context): GoogleAuthUiClient =
//        GoogleAuthUiClient(ctx)
}
