package com.example.marvel.util

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.marvel.domain.Character
import com.example.marvel.domain.Comic
import com.example.marvel.domain.Event

@BindingAdapter("characterName")
fun TextView.setCharacterName(item : Character?) {
    item?.let {
        text = it.name
    }
}

@BindingAdapter("characterImgUrl")
fun ImageView.setCharacterImage(item: Character?) {
    item?.let {
        val imgUri = item.thumbnail?.toUri()?.buildUpon()?.scheme("http")?.build()
        Glide.with(context)
            .load(imgUri)
            .into(this)
    }
}

@BindingAdapter("comicName")
fun TextView.setComicName(item : Comic?) {
    item?.let {
        text = it.title
    }
}

@BindingAdapter("comicImgUrl")
fun ImageView.setComicImage(item: Comic?) {
    item?.let {
        val imgUri = item.thumbnail?.toUri()?.buildUpon()?.scheme("http")?.build()
        Glide.with(context)
            .load(imgUri)
            .into(this)
    }
}

@BindingAdapter("pageCount")
fun TextView.setPageCount(item : Comic?) {
    item?.let {
        val str = "Page count = " + it.pageCount.toString()
        text = str
    }
}

@BindingAdapter("eventImgUrl")
fun ImageView.setEventImage(item: Event?) {
    item?.let {
        val imgUri = item.thumbnail?.toUri()?.buildUpon()?.scheme("http")?.build()
        Glide.with(context)
            .load(imgUri)
            .into(this)
    }
}

@BindingAdapter("eventName")
fun TextView.setComicName(item : Event?) {
    item?.let {
        text = it.title
    }
}
