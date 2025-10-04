package com.sample.noteapp.presentation.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.noteapp.data.model.Note
import com.sample.noteapp.domain.viewState.NoteViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val noteRepository: com.sample.noteapp.domain.repository.NoteRepository
) : ViewModel() {

    private val _noteState = MutableStateFlow<NoteViewState>(NoteViewState.Loading)
    val noteState: StateFlow<NoteViewState> = _noteState
    init {
        fetchNotes()
    }
    private fun fetchNotes() {
        viewModelScope.launch {
                _noteState.value = NoteViewState.Loading
            noteRepository.getNoteListing().collect { notes ->
                _noteState.value = if (notes.isEmpty()) {
                    NoteViewState.Empty
                } else {
                    NoteViewState.Success(notes)
                }
            }
        }
    }
    fun addNote(title: String, description: String) {
        viewModelScope.launch {
            val newNote = Note(
                title = title,
                description = description,
                timestamp = System.currentTimeMillis()
            )
            noteRepository.insertNote(newNote)
        }
    }
    fun deleteNote(noteId: Int) {
        viewModelScope.launch {
            noteRepository.deleteNote(noteId)
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch {
            noteRepository.updateNote(note)
        }
    }
}