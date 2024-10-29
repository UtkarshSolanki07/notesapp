package com.example.notesapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.notesapp.Note
import java.util.*

class AddNoteActivity : AppCompatActivity() {

    private lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        // Setting up the toolbar
        setSupportActionBar(findViewById(R.id.addNoteToolbar))
        val toolbarTitle: TextView = findViewById(R.id.addNoteToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Add com.example.notesapp.Note"

        // Initializing ViewModel
        noteViewModel = ViewModelProvider(this)[NoteViewModel::class.java]

        // Save button click listener
        val saveButton: Button = findViewById(R.id.buttonSave)
        saveButton.setOnClickListener {
            saveNote()
        }
    }

    private fun saveNote() {
        val title: EditText = findViewById(R.id.editTextTitle)
        val content: EditText = findViewById(R.id.editTextContent)

        val titleText = title.text.toString().trim()
        val contentText = content.text.toString().trim()

        if (titleText.isEmpty() || contentText.isEmpty()) {
            Toast.makeText(this, "Please enter both title and content", Toast.LENGTH_SHORT).show()
            return
        }

        val note = Note(
            title = titleText,
            content = contentText,
            timestamp = Date().time
        )

        noteViewModel.insert(note)
        Toast.makeText(this, "com.example.notesapp.Note saved", Toast.LENGTH_SHORT).show()
        finish()
    }
}