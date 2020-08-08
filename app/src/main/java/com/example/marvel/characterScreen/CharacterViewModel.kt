package com.example.marvel.characterScreen

import android.app.Application
import androidx.lifecycle.*
import com.example.marvel.database.getDatabase
import com.example.marvel.repository.CharacterRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class CharacterViewModel(private val characterRepository: CharacterRepository) : ViewModel() {

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
                characterRepository.refreshCharacters()
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

    fun finishDialog() {
        _exception.value= null
    }

    val charactersList = characterRepository.characters
}