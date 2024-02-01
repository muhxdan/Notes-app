package com.salt.apps.notes.data.repository

import com.salt.apps.notes.data.local.entity.NoteEntity
import com.salt.apps.notes.data.local.room.dao.NoteDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(private val noteDao: NoteDao) {
    suspend fun insertNote(noteEntity: NoteEntity) = noteDao.upsertNote(noteEntity = noteEntity)
    fun getAllNotes(): Flow<List<NoteEntity>> = noteDao.getAllNotes()
    suspend fun deleteNote(noteEntity: NoteEntity) = noteDao.deleteNote(noteEntity = noteEntity)
    fun deleteAllNotes() = noteDao.deleteAllNotes()
}