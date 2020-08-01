package com.example.marvel.characterScreen

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.marvel.databinding.LayoutBinding
import com.example.marvel.domain.Character

class CharacterAdapter : ListAdapter<Character, CharacterAdapter.ViewHolder>(CharacterDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder private constructor(private val binding : LayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(character: Character) {
            binding.character = character
            binding.info.setOnClickListener{view->
                val completeUrl = character.url
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

class CharacterDiffCallback : DiffUtil.ItemCallback<Character>() {

    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem == newItem
    }
}