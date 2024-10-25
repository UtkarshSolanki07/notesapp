package com.example.notesapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.adapter.NoteAdapter
import com.example.notesapp.model.NoteDatabase
import com.example.notesapp.model.NoteRepository
import com.example.notesapp.ui.NoteDialogFragment
import com.example.notesapp.viewmodel.NoteViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var noteViewModel: NoteViewModel
    private lateinit var noteAdapter: NoteAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var fab: FloatingActionButton
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Toolbar setup
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Setup RecyclerView
        recyclerView = findViewById(R.id.recyclerView)
        noteAdapter = NoteAdapter(listOf())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = noteAdapter

        // Floating Action Button setup
        fab = findViewById(R.id.fab)

        // Database setup
        val database = NoteDatabase.getDatabase(this)
        val repository = NoteRepository(database.noteDao())
        noteViewModel = NoteViewModel(repository)

        // Load all notes
        CoroutineScope(Dispatchers.IO).launch {
            val notes = noteViewModel.getAllNotes()
            runOnUiThread { noteAdapter.setNotes(notes) }
        }

        // Add new note on FAB click
        fab.setOnClickListener {
            NoteDialogFragment(null) { newNote ->
                CoroutineScope(Dispatchers.IO).launch {
                    noteViewModel.insert(newNote)
                    val notes = noteViewModel.getAllNotes()
                    runOnUiThread { noteAdapter.setNotes(notes) }
                }
            }.show(supportFragmentManager, "AddNoteDialog")
        }

        // Setup SearchView for filtering notes
        searchView = findViewById(R.id.searchView)
        setupSearch()
    }

    private fun setupSearch() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?) = false

            override fun onQueryTextChange(newText: String?): Boolean {
                CoroutineScope(Dispatchers.IO).launch {
                    val filteredNotes = if (newText.isNullOrEmpty()) {
                        noteViewModel.getAllNotes()
                    } else {
                        noteViewModel.getAllNotes().filter {
                            it.title.contains(newText, ignoreCase = true) ||
                                    it.content.contains(newText, ignoreCase = true)
                        }
                    }
                    runOnUiThread { noteAdapter.setNotes(filteredNotes) }
                }
                return true
            }
        })
    }
}
