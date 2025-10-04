package com.sample.noteapp.domain.viewState

import com.sample.noteapp.data.model.Note


sealed interface NoteViewState {
    object Loading : NoteViewState
    object Empty : NoteViewState
    data class Success(val notes: List<Note>) : NoteViewState
}