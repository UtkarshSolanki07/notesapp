package com.example.notesapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.model.Note
import com.example.notesapp.model.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel(private val repository: NoteRepository) : ViewModel() {

    fun insert(note: Note) = viewModelScope.launch { repository.insert(note) }
    fun update(note: Note) = viewModelScope.launch { repository.update(note) }
    fun delete(note: Note) = viewModelScope.launch { repository.delete(note) }
    suspend fun getAllNotes(): List<Note> = repository.getAllNotes()
}
