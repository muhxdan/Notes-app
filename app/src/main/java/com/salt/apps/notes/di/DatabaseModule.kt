package com.salt.apps.notes.di

import android.content.Context
import androidx.room.Room
import com.salt.apps.notes.data.local.room.dao.NoteDao
import com.salt.apps.notes.data.local.room.database.Database
import com.salt.apps.notes.util.Constants.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): Database {
        return Room.databaseBuilder(
            context = context,
            klass = Database::class.java,
            name = DB_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideUserDao(database: Database): NoteDao = database.noteDao()
}