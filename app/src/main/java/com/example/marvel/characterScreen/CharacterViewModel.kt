package com.example.marvel.characterScreen

import android.app.Application
import androidx.lifecycle.*
import com.example.marvel.database.getDatabase
import com.example.marvel.repository.CharacterRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class CharacterViewModel(application: Application) : ViewModel() {

    private val database = getDatabase(application)
    private val characterRepository = CharacterRepository(database)

    private val _exception = MutableLiveData<Exception>()

    val exception : LiveData<Exception>
    get() = _exception


    init {
        viewModelScope.launch {
            try {
                characterRepository.refreshCharacters()
            } catch (e : Exception) {
                _exception.value = e
            }
        }
    }

    fun finishDialog() {
        _exception.value= null
    }

    val charactersList = characterRepository.characters

    class Factory(private val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CharacterViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CharacterViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}