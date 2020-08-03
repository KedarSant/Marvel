package com.example.marvel.characterScreen

import android.app.Application
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.marvel.database.getDatabase
import com.example.marvel.repository.CharacterRepository
import kotlinx.coroutines.launch

class CharacterViewModel(private val application: Application, private val activity: FragmentActivity) : ViewModel() {

    private val database = getDatabase(application)
    private val characterRepository = CharacterRepository(database,activity)


    init {
        viewModelScope.launch {
            characterRepository.refreshCharacters()
        }
    }

    val charactersList = characterRepository.characters

    class Factory(private val app: Application, private val activity: FragmentActivity) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CharacterViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CharacterViewModel(app,activity) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}