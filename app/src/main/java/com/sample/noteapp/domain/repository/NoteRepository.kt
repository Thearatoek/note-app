package com.sample.noteapp.domain.repository

import com.sample.noteapp.data.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    suspend fun  getNoteListing() : Flow<List<Note>>
    suspend fun insertNote(note: Note)
    suspend fun deleteNote(noteId: Int)
    suspend fun updateNote(note: Note)
}