package com.sample.noteapp.data.repository

import com.sample.noteapp.data.local.dao.NoteDao
import com.sample.noteapp.data.mapper.toDomain
import com.sample.noteapp.data.model.Note
import com.sample.noteapp.data.model.NoteEntity
import com.sample.noteapp.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao
) : NoteRepository {
    override suspend fun getNoteListing(): Flow<List<Note>> = flow{
        val response = noteDao.getAllNotes()
        emitAll(response.map { it.toDomain() })
    }
    override suspend fun insertNote(note: Note) {
        noteDao.insertNote(
            NoteEntity(
                id = note.id,
                title = note.title,
                description = note.description,
                timestamp = note.timestamp
            )
        )
    }
    override suspend fun deleteNote(noteId: Int) {
       noteDao.deleteNote(noteId)
    }
    override suspend fun updateNote(note: Note) {
        noteDao.updateNote(
            NoteEntity(
                id = note.id,
                title = note.title,
                description = note.description,
                timestamp = note.timestamp
            )
        )
    }
    override suspend fun getNoteById(noteId: Int): Note? {
        val noteEntity = noteDao.getNoteById(noteId)
        return noteEntity?.toDomain()
    }
}