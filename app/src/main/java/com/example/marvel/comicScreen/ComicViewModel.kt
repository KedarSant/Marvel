package com.example.marvel.comicScreen

import androidx.lifecycle.*
import com.example.marvel.repository.ComicsRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class ComicViewModel(private val comicsRepository: ComicsRepository) : ViewModel() {

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
                comicsRepository.refreshComics()
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

    val comicsList = comicsRepository.comics
}