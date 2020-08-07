package com.example.marvel.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.marvel.database.MarvelDatabase
import com.example.marvel.database.asDomainCharacter
import com.example.marvel.database.asDomainComic
import com.example.marvel.database.asDomainEvent
import com.example.marvel.domain.Character
import com.example.marvel.domain.Comic
import com.example.marvel.domain.Event
import com.example.marvel.network.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class CharacterRepository(private val database: MarvelDatabase) {

    private lateinit var characterWrapper: CharacterDataWrapper

    val characters: LiveData<List<Character>> =
        Transformations.map(database.characterDao.getCharacters()) {
            it?.asDomainCharacter()
        }

    suspend fun refreshCharacters() {
        try {
            withContext(Dispatchers.IO) {
                characterWrapper = Network.info.getCharactersAsync(Network.ts,Network.apikey,Network.hash,100).await()
                characterWrapper.asDatabaseCharacter()?.let { database.characterDao.insertCharacters(*it) }
            }
        } catch (e : Exception) {
            throw(e)
        }
    }
}

class ComicsRepository(private val database: MarvelDatabase) {

    private lateinit var comicWrapper: ComicDataWrapper

    val comics: LiveData<List<Comic>> =
        Transformations.map(database.comicsDao.getComics()) {
            it?.asDomainComic()
        }

    suspend fun refreshComics() {
        try {
            withContext(Dispatchers.IO) {
                comicWrapper = Network.info.getComicsAsync(Network.ts,Network.apikey,Network.hash,100).await()
                comicWrapper.asDatabaseComic()?.let { database.comicsDao.insertComics(*it) }
            }
        } catch (e : Exception) {
            throw(e)
        }
    }
}

class EventsRepository(private val database: MarvelDatabase) {

    private lateinit var eventWrapper: EventDataWrapper

    val events: LiveData<List<Event>> =
        Transformations.map(database.eventsDao.getEvents()) {
            it?.asDomainEvent()
        }

    suspend fun refreshEvents() {
        try {
            withContext(Dispatchers.IO) {
                eventWrapper = Network.info.getEventsAsync(Network.ts,Network.apikey,Network.hash,100).await()
                eventWrapper.asDatabaseEvent()?.let { database.eventsDao.insertEvents(*it) }
            }
        } catch (e : Exception) {
            throw (e)
        }
    }
}
