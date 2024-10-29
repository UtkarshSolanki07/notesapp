// AddNoteActivity.kt
package com.example.notesapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AddNoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        // Initialize UI elements
        val titleEditText: EditText = findViewById(R.id.editTextTitle)
        val contentEditText: EditText = findViewById(R.id.editTextContent)
        val saveButton: Button = findViewById(R.id.buttonSaveNote)

        // Set click listener on save button
        saveButton.setOnClickListener {
            val title = titleEditText.text.toString()
            val content = contentEditText.text.toString()

            // Pass the note data back if fields are not empty
            if (title.isNotEmpty() && content.isNotEmpty()) {
                val resultIntent = Intent()
                resultIntent.putExtra("title", title)
                resultIntent.putExtra("content", content)
                setResult(Activity.RESULT_OK, resultIntent)
                finish() // Close this activity
            }
        }
    }
}
