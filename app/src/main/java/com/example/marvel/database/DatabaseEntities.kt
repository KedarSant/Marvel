package com.example.marvel.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.marvel.domain.Character
import com.example.marvel.domain.Comic
import com.example.marvel.domain.Event
import com.example.marvel.network.Image


@Entity
data class DatabaseCharacter(
    @PrimaryKey
    val id : Int?,   //The unique ID of the character resource
    val name : String?, //The name of the character
    val description : String?, // A short bio or description of the character
    val thumbnail : String?, //The representative image for this character
    val url : String?
)

@Entity
data class DatabaseComic(
    @PrimaryKey
    val id : Int?, // The unique ID of the comic resource
    val title : String?,  // The canonical title of the comic
    val issueNumber : Double?,  // The number of the issue in the series (will generally be 0 for collection formats)
    val variantDescription : String?,  // If the issue is a variant
    val pageCount : Int?,  // The number of story pages in the comic
    val thumbnail : String?,  // The representative image for this comic
    val description : String?, // The preferred description of the comic
    val url : String?
)

@Entity
data class DatabaseEvent(
    @PrimaryKey
    val id : Int?, // The unique ID of the event resource
    val title : String?, // The title of the event
    val description : String?, // A description of the event
    val thumbnail : String?, // The representative image for this event
    val url : String?
)

fun List<DatabaseCharacter>.asDomainCharacter() : List<Character> {
    return map {databaseCharacter->
        Character(
            id = databaseCharacter.id,
            name = databaseCharacter.name,
            thumbnail = databaseCharacter.thumbnail,
            description = databaseCharacter.description,
            url = databaseCharacter.url
        )
    }
}

fun List<DatabaseComic>.asDomainComic() : List<Comic> {
    return map {databaseComic ->
        Comic(
            id = databaseComic.id,
            title = databaseComic.title,
            issueNumber = databaseComic.issueNumber,
            variantDescription = databaseComic.variantDescription,
            description = databaseComic.description,
            thumbnail = databaseComic.thumbnail,
            pageCount = databaseComic.pageCount,
            url = databaseComic.url
        )
    }
}

fun List<DatabaseEvent>.asDomainEvent() : List<Event> {
    return map {databaseEvent ->
        Event(
            id = databaseEvent.id,
            title = databaseEvent.title,
            thumbnail = databaseEvent.thumbnail,
            description = databaseEvent.description,
            url = databaseEvent.url
        )
    }
}