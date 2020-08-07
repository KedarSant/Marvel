package com.example.marvel.eventsScreen

import android.app.Application
import androidx.lifecycle.*
import com.example.marvel.database.getDatabase
import com.example.marvel.repository.EventsRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class EventViewModel(private val application: Application) : ViewModel() {

    private val database = getDatabase(application)
    private val eventsRepository = EventsRepository(database)

    private val _exception = MutableLiveData<Exception>()

    val exception : LiveData<Exception>
    get() = _exception


    init {
        viewModelScope.launch {
            try {
                eventsRepository.refreshEvents()
            } catch (e : Exception) {
                _exception.value = e
            }
        }
    }

    fun finishedDialog() {
        _exception.value = null
    }

    val eventsList = eventsRepository.events

    class Factory(private val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(EventViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return EventViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}