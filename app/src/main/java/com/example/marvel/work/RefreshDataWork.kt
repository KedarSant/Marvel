package com.example.marvel.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import com.example.marvel.repository.CharacterRepository
import com.example.marvel.repository.ComicsRepository
import com.example.marvel.repository.EventsRepository
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.HttpException
import java.util.*

class RefreshDataWorker(appContext: Context, params: WorkerParameters): CoroutineWorker(appContext,params),KoinComponent {

    companion object {
        const val WORK_NAME = "RefreshDataWorker"
    }

    private val characterRepository : CharacterRepository by inject()
    private val comicsRepository : ComicsRepository by inject()
    private val eventsRepository : EventsRepository by inject()

    override suspend fun doWork(): Result {
        return try {
            characterRepository.refreshCharacters()
            comicsRepository.refreshComics()
            eventsRepository.refreshEvents()
            Result.success()
        } catch (e: HttpException) {
            Result.retry()
        }
    }
}