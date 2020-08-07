package com.example.marvel.comicScreen

import android.app.Application
import androidx.lifecycle.*
import com.example.marvel.database.getDatabase
import com.example.marvel.repository.ComicsRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class ComicViewModel(comicsRepository: ComicsRepository) : ViewModel() {

    private val _exception = MutableLiveData<Exception>()

    val exception : LiveData<Exception>
    get() = _exception

    init {
        viewModelScope.launch {
            try {
                comicsRepository.refreshComics()
            } catch (e : Exception) {
                _exception.value = e
            }
        }
    }

    fun finishedDialog() {
        _exception.value = null
    }

    val comicsList = comicsRepository.comics
}