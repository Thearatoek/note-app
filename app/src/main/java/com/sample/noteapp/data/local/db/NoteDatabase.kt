package com.sample.noteapp.data.local.db
import androidx.room.Database
import androidx.room.RoomDatabase
import com.sample.noteapp.data.local.dao.NoteDao
import com.sample.noteapp.data.model.NoteEntity

@Database(
    entities = [NoteEntity::class],
    version = 1,
    exportSchema = false
)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}
