package com.example.todonotes

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.example.todonotes.databinding.CardBinding

class NotesAdapter : RecyclerView.Adapter<NotesAdapter.viewholder>() {
    private var Noteslist: List<Note> = ArrayList<Note>()
    var onItemClick: ((Note) -> Unit)? = null

    inner class viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var date: TextView
        lateinit var title: TextView
        lateinit var description: TextView

        init {
            date = itemView.findViewById(R.id.date)
            title = itemView.findViewById(R.id.title_txv)
            description = itemView.findViewById(R.id.description_txv)
            itemView.setOnClickListener {
                onItemClick?.invoke(Noteslist[adapterPosition])


            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card, parent, false)

        return viewholder(view)
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        val SingleNoteItem = Noteslist.get(position)
        holder.date.text = SingleNoteItem.date
        holder.title.text = SingleNoteItem.title
        holder.description.text = SingleNoteItem.description

    }

    override fun getItemCount(): Int {

        return Noteslist.size
    }

    fun setNotesList(list: List<Note>) {
        Noteslist = list
        notifyDataSetChanged()

    }

    fun getItemAtPosition(position: Int): Note {
        return Noteslist.get(position)
    }


}