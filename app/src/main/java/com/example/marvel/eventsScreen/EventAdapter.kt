package com.example.marvel.eventsScreen

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.marvel.databinding.LayoutBinding
import com.example.marvel.domain.Event

class EventAdapter : ListAdapter<Event, EventAdapter.ViewHolder>(EventDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder private constructor(private val binding : LayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(event : Event) {
            binding.event = event
            binding.info.setOnClickListener{view->
                val completeUrl = event.url
                val browserIntent =
                    Intent(Intent.ACTION_VIEW, Uri.parse(completeUrl))
                view.context.startActivity(browserIntent)
            }
        }

        companion object {
            fun from(parent: ViewGroup) : ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = LayoutBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class EventDiffCallback : DiffUtil.ItemCallback<Event>() {

    override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem : Event, newItem: Event): Boolean {
        return oldItem == newItem
    }
}