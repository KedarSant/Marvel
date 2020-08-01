package com.example.marvel.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MarvelApiService{

    @GET("characters")
    fun getCharactersAsync(
        @Query("ts")ts: String,
        @Query("apikey")apikey : String,
        @Query("hash")hash : String,
        @Query("limit")limit : Int) : Deferred<CharacterDataWrapper>

    @GET("comics")
    fun getComicsAsync(
        @Query("ts")ts: String,
        @Query("apikey")apikey : String,
        @Query("hash")hash : String,
        @Query("limit")limit : Int) : Deferred<ComicDataWrapper>

    @GET("events")
    fun getEventsAsync(
        @Query("ts")ts: String,
        @Query("apikey")apikey : String,
        @Query("hash")hash : String,
        @Query("limit")limit : Int) : Deferred<EventDataWrapper>
}


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


object Network {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://gateway.marvel.com/v1/public/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val info: MarvelApiService = retrofit.create(MarvelApiService::class.java)
    const val apikey = "0cbd08342b0a8487103c7bdb81ebd9b6"
    const val ts = "J4ZUHWB8dqussr8"
    const val hash = "53d5d32af3551ea4772b3486061e9450"
}
