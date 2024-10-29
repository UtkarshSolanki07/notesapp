package com.example.notesapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.notesapp.Note
import com.example.notesapp.R

class NoteDialogFragment(private val note: Note?, private val onSave: (Note) -> Unit) : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_note, container, false)

        val titleInput = view.findViewById<EditText>(R.id.editTextTitle)
        val contentInput = view.findViewById<EditText>(R.id.editTextContent)

        note?.let {
            titleInput.setText(it.title)
            contentInput.setText(it.content)
        }

        view.findViewById<Button>(R.id.buttonSave).setOnClickListener {
            val title = titleInput.text.toString().trim()
            val content = contentInput.text.toString().trim()

            if (title.isNotEmpty() && content.isNotEmpty()) {
                val updatedNote = note?.copy(title = title, content = content) ?: Note(
                    title = title, content = content)
                onSave(updatedNote)
                dismiss()
            } else {
                Toast.makeText(context, "Fields cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }
        return view
    }
}
