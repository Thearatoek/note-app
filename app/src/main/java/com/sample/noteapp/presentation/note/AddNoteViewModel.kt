package com.sample.noteapp.presentation.note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.noteapp.data.model.Note
import com.sample.noteapp.domain.repository.NoteRepository
import com.sample.noteapp.domain.viewState.AddNoteViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNewItemViewModel @Inject constructor(
    private val noteRepository: NoteRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddNoteViewState())
    val uiState: StateFlow<AddNoteViewState> = _uiState

    fun onTitleChange(value: String) {
        _uiState.update { it.copy(title = value) }
    }

    fun onDescriptionChange(value: String) {
        _uiState.update { it.copy(description = value) }
    }

    fun onSaveClick() {
        val current = _uiState.value
        var isValid = true
        if (current.title.isBlank()) {
            _uiState.update { it.copy(textError = "Name cannot be empty") }
            isValid = false
        }
        if (!isValid) return
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                viewModelScope.launch {
                    val newNote = Note(
                        title = current.title,
                        description = current.description,
                        timestamp = System.currentTimeMillis()
                    )
                    noteRepository.insertNote(newNote)
                }
                _uiState.update { it.copy(isLoading = false) }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }
}
