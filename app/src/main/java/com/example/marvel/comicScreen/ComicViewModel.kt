package com.example.marvel.comicScreen

import android.app.Application
import androidx.lifecycle.*
import com.example.marvel.database.getDatabase
import com.example.marvel.repository.ComicsRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class ComicViewModel(application: Application) : ViewModel() {

    private val database = getDatabase(application)
    private val comicsRepository = ComicsRepository(database)

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

    class Factory(private val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ComicViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ComicViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}