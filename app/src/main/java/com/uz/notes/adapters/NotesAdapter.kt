package com.uz.notes.adapters

import android.view.View
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.uz.notes.model.Note


/**
 * Created by Eldor Turgunov on 04.09.2022.
 * Notes
 * eldorturgunov777@gmail.com
 */
class NotesAdapter:ListAdapter<Note, NotesAdapter.NotesViewHolder>(DiffUtilCallback) {

    inner class NotesViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

    }

}