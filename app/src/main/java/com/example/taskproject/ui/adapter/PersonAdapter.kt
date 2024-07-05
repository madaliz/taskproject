package com.example.taskproject.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.taskproject.R
import com.example.taskproject.domain.model.Persons

class PersonAdapter : ListAdapter<Persons, PersonAdapter.PersonViewHolder>(DIFF_CALLBACK) {

    var itemClickListener: ((item: Persons, position: Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_person, parent, false)
        return PersonViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    fun setOnItemClickListener(listener: (Persons, Int) -> Unit) {
        itemClickListener = listener
    }

    inner class PersonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val personName: TextView = itemView.findViewById(R.id.personName)
        private val item: LinearLayout = itemView.findViewById(R.id.item)

        fun bind(person: Persons) {
            personName.text = person.username
            item.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    itemClickListener?.invoke(getItem(position), position)
                }
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Persons>() {
            override fun areItemsTheSame(oldItem: Persons, newItem: Persons): Boolean {
                return oldItem.userId == newItem.userId
            }

            override fun areContentsTheSame(oldItem: Persons, newItem: Persons): Boolean {
                return oldItem == newItem
            }
        }
    }


    fun addPerson(person: Persons) {
        val newList = currentList.toMutableList()
        newList.add(person)
        submitList(newList)
    }


    fun updatePersons(newList: List<Persons>) {
        submitList(newList)
    }
}