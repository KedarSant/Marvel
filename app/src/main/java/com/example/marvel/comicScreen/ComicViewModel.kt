package com.example.marvel.comicScreen

import android.app.Application
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.marvel.database.getDatabase
import com.example.marvel.repository.ComicsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class ComicViewModel(private val application: Application, private val activity: FragmentActivity) : ViewModel() {

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val database = getDatabase(application)
    private val comicsRepository = ComicsRepository(database,activity)


    init {
        viewModelScope.launch {
            comicsRepository.refreshComics()
        }
    }

    val comicsList = comicsRepository.comics

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    class Factory(private val app: Application, private val activity: FragmentActivity) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ComicViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ComicViewModel(app,activity) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}