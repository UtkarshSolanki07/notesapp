package com.example.notesapp.model

class NoteRepository(private val noteDao: NoteDao) {

    suspend fun insert(note: Note) = noteDao.insert(note)
    suspend fun update(note: Note) = noteDao.update(note)
    suspend fun delete(note: Note) = noteDao.delete(note)
    suspend fun getAllNotes(): List<Note> = noteDao.getAllNotes()
}
