package com.sample.noteapp.data

import android.content.Context
import androidx.room.Room
import com.sample.noteapp.data.local.dao.NoteDao
import com.sample.noteapp.data.local.db.NotesDatabase
import com.sample.noteapp.data.repository.NoteRepositoryImpl
import com.sample.noteapp.domain.repository.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideNotesDatabase(@ApplicationContext context: Context): NotesDatabase {
        return Room.databaseBuilder(
            context,
            NotesDatabase::class.java,
            "notes_database"
        ).build()
    }
    @Provides
    @Singleton
    fun provideNoteDao(database: NotesDatabase): NoteDao {
        return database.noteDao()
    }
    @Provides
    @Singleton
    fun provideNoteRepository(noteDao: NoteDao): NoteRepository {
        return NoteRepositoryImpl(noteDao)
    }
}