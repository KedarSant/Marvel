package com.example.marvel.network

import kotlinx.coroutines.Deferred
import retrofit2.http.GET


interface MarvelApiService{

    @GET("characters?ts=J4ZUHWB8dqussr8&apikey=0cbd08342b0a8487103c7bdb81ebd9b6" +
            "&hash=53d5d32af3551ea4772b3486061e9450&limit=100")
    fun getCharactersAsync() : Deferred<CharacterDataWrapper>

    @GET("comics?ts=J4ZUHWB8dqussr8&apikey=0cbd08342b0a8487103c7bdb81ebd9b6" +
            "&hash=53d5d32af3551ea4772b3486061e9450&limit=100")
    fun getComicsAsync() : Deferred<ComicDataWrapper>

    @GET("events?ts=J4ZUHWB8dqussr8&apikey=0cbd08342b0a8487103c7bdb81ebd9b6" +
            "&hash=53d5d32af3551ea4772b3486061e9450&limit=100")
    fun getEventsAsync() : Deferred<EventDataWrapper>
}

