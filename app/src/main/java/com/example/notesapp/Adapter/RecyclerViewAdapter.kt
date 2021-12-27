package com.example.notesapp.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.Model.Note
import com.example.notesapp.databinding.ItemRowBinding

class RecyclerViewAdapter( private var noteList: ArrayList<Note>): RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>()  {
    class ItemViewHolder(val binding: ItemRowBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return  ItemViewHolder(ItemRowBinding.inflate(
            LayoutInflater.from(parent.context)
            , parent,
            false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val notes = noteList[position]
        holder.binding.apply {
            val noteData = "${notes.pk} : ${notes.note}"
            tvNote.text = noteData

        }
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    fun update(noteList: ArrayList<Note>){
        this.noteList = noteList
        notifyDataSetChanged()
    }
}