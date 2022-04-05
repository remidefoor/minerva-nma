package be.howest.defoor.remi.minerva.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import be.howest.defoor.remi.minerva.R
import be.howest.defoor.remi.minerva.model.Note

class NoteAdapter(private val notes: List<Note>) {
    class NoteViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.note_note)
    }
}