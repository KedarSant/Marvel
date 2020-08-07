package com.example.marvel.di

import androidx.room.Room
import com.example.marvel.characterScreen.CharacterViewModel
import com.example.marvel.comicScreen.ComicViewModel
import com.example.marvel.database.MarvelDatabase
import com.example.marvel.eventsScreen.EventViewModel
import com.example.marvel.network.MarvelApiService
import com.example.marvel.repository.CharacterRepository
import com.example.marvel.repository.ComicsRepository
import com.example.marvel.repository.EventsRepository
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val viewModelModule = module {

    viewModel { EventViewModel(get()) }
    viewModel { CharacterViewModel(get()) }
    viewModel { ComicViewModel(get()) }

}

val databaseModule = module {
    single {
        Room.databaseBuilder(androidApplication(),MarvelDatabase::class.java,"Marvel")
            .fallbackToDestructiveMigration().build()
    }
}

val repositoryModule = module {
    single {
        EventsRepository(get(),get())
    }
    single {
        CharacterRepository(get(),get())
    }
    single {
        ComicsRepository(get(),get())
    }
}

val networkModule = module {
    single {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://gateway.marvel.com/v1/public/")
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    single {
        get<Retrofit>().create(MarvelApiService::class.java)
    }
}

val appModules = listOf(viewModelModule, databaseModule, repositoryModule, networkModule)