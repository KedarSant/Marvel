package com.example.marvel.eventsScreen

import android.app.Application
import androidx.lifecycle.*
import com.example.marvel.database.getDatabase
import com.example.marvel.repository.EventsRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class EventViewModel(private val eventsRepository: EventsRepository) : ViewModel() {

    private val _exception = MutableLiveData<Exception>()

    val exception : LiveData<Exception>
    get() = _exception

    private val _refreshing = MutableLiveData<Boolean>()

    val refreshing : LiveData<Boolean>
        get() = _refreshing

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            try {
                eventsRepository.refreshEvents()
            } catch (e : Exception) {
                _exception.value = e
            }
        }.invokeOnCompletion {
            _refreshing.value = false
        }
    }

    fun doneRefreshing() {
        _refreshing.value = null
    }

    fun finishedDialog() {
        _exception.value = null
    }

    val eventsList = eventsRepository.events
}