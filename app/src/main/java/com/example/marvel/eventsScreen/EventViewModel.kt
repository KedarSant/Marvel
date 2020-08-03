package com.example.marvel.eventsScreen

import android.app.Application
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.marvel.database.getDatabase
import com.example.marvel.repository.EventsRepository
import kotlinx.coroutines.launch

class EventViewModel(private val application: Application, private val activity: FragmentActivity) : ViewModel() {

    private val database = getDatabase(application)
    private val eventsRepository = EventsRepository(database,activity)


    init {
        viewModelScope.launch {
            eventsRepository.refreshEvents()
        }
    }

    val eventsList = eventsRepository.events

    class Factory(private val app: Application, private val activity: FragmentActivity) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(EventViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return EventViewModel(app,activity) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}