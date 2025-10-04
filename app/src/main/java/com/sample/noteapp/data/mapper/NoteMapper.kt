package com.sample.noteapp.data.mapper

import com.sample.noteapp.data.model.Note
import com.sample.noteapp.data.model.NoteEntity


fun NoteEntity.toDomain(): Note {
    return Note(
        id = this.id,
        title = this.title,
        description = this.description,
        timestamp = this.timestamp
    )
}

fun List<NoteEntity>.toDomain(): List<Note> {
    return this.map { it.toDomain() }
}