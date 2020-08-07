package com.example.marvel.eventsScreen

import android.app.Application
import androidx.lifecycle.*
import com.example.marvel.database.getDatabase
import com.example.marvel.repository.EventsRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class EventViewModel(eventsRepository: EventsRepository) : ViewModel() {

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
}