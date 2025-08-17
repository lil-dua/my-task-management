package dev.ryan.core.database.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.ryan.core.database.MtmDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {
    @Provides
    @Singleton
    fun providesMtmDatabase(
        @ApplicationContext context: Context,
    ): MtmDatabase = Room.databaseBuilder(
        context,
        MtmDatabase::class.java,
        "mtm-database",
    ).build()
}