package com.example.marvel.characterScreen

import android.app.Application
import androidx.lifecycle.*
import com.example.marvel.database.getDatabase
import com.example.marvel.repository.CharacterRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class CharacterViewModel(characterRepository: CharacterRepository) : ViewModel() {

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
}