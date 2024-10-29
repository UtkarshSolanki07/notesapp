package com.example.notesapp
import com.example.notesapp.NoteAdapter
import com.example.notesapp.NoteViewModel
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var noteAdapter: NoteAdapter
    private lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Setting up the toolbar title
        setSupportActionBar(findViewById(R.id.toolbar))
        val toolbarTitle: TextView = findViewById(R.id.toolbarTitle)
        toolbarTitle.text = "NotesApp"

        // RecyclerView setup
        setupRecyclerView()

        // Initializing ViewModel
        noteViewModel = ViewModelProvider(this)[NoteViewModel::class.java]
        noteViewModel.getAllNotes().observe(this) { notes ->
            noteAdapter.updateNotes(notes)
        }

        // FloatingActionButton click listener
        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this, AddNoteActivity::class.java)
            startActivity(intent)
        }

        // SearchView setup
        val searchView: SearchView = findViewById(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    noteAdapter.filterNotes(newText)
                }
                return true
            }
        })
    }

    private fun setupRecyclerView() {
        noteAdapter = NoteAdapter()
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = noteAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        noteViewModel.getAllNotes().observe(this) { notes ->
            noteAdapter.updateNotes(notes)
        }
    }
}