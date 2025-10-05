package com.sample.noteapp.presentation.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.noteapp.data.model.Note
import com.sample.noteapp.domain.viewState.DashboardViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val noteRepository: com.sample.noteapp.domain.repository.NoteRepository
) : ViewModel() {

    private val _noteState = MutableStateFlow<DashboardViewState>(DashboardViewState.Loading)
    val noteState: StateFlow<DashboardViewState> = _noteState
    init {
        fetchNotes()
    }
    private fun fetchNotes() {
        viewModelScope.launch {
                _noteState.value = DashboardViewState.Loading
            noteRepository.getNoteListing().collect { notes ->
                _noteState.value = if (notes.isEmpty()) {
                    DashboardViewState.Empty
                } else {
                    DashboardViewState.Success(notes)
                }
            }
        }
    }
    fun deleteNote(noteId: Int) {
        viewModelScope.launch {
            noteRepository.deleteNote(noteId)
        }
    }

}