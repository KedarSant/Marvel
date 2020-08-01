package com.example.marvel.comicScreen

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.marvel.databinding.LayoutBinding
import com.example.marvel.domain.Comic

class ComicAdapter : ListAdapter<Comic, ComicAdapter.ViewHolder>(ComicDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder private constructor(private val binding : LayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(comic: Comic) {
            binding.comic = comic
            binding.info.setOnClickListener{view->
                val completeUrl = comic.url
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

class ComicDiffCallback : DiffUtil.ItemCallback<Comic>() {

    override fun areItemsTheSame(oldItem: Comic, newItem: Comic): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Comic, newItem: Comic): Boolean {
        return oldItem == newItem
    }
}