package com.sample.noteapp.domain.viewState

import com.sample.noteapp.data.model.Note


sealed interface DashboardViewState {
    object Loading : DashboardViewState
    object Empty : DashboardViewState
    data class Success(val notes: List<Note>) : DashboardViewState
}