package com.sample.noteapp.domain.viewState

data class AddNoteViewState(
    val title: String = "",
    val description: String = "",
    val textError: String? = null,
    val isLoading: Boolean = false,
)
