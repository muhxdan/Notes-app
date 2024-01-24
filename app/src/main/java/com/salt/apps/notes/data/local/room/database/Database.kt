package com.salt.apps.notes.data.local.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.salt.apps.notes.data.local.entity.NoteEntity
import com.salt.apps.notes.data.local.room.dao.NoteDao
import com.salt.apps.notes.util.Constants

@Database(
    entities = [NoteEntity::class],
    version = Constants.DB_VERSION,
    exportSchema = false
)

abstract class Database : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}