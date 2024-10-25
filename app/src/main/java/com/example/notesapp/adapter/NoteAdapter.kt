package com.example.notesapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.model.Note

class NoteAdapter(
    private var notes: List<Note>,
    private val onItemClickListener: ((Note) -> Unit)? = null
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    // ViewHolder class to hold view references for each item
    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.textViewTitle)
        val contentTextView: TextView = itemView.findViewById(R.id.textViewContent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.titleTextView.text = note.title
        holder.contentTextView.text = note.content

        // Item click listener
        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(note)
        }
    }

    override fun getItemCount() = notes.size

    // Update adapter data
    fun setNotes(newNotes: List<Note>) {
        notes = newNotes
        notifyDataSetChanged()
    }
}
