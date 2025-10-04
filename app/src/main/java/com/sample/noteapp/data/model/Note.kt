package com.sample.noteapp.data.model


data class Note(
    val id: Int = 0,
    val title: String,
    val description: String,
    val timestamp: Long
)