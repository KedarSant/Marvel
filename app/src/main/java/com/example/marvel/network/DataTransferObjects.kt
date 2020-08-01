package com.example.marvel.network

import com.example.marvel.database.DatabaseCharacter
import com.example.marvel.database.DatabaseComic
import com.example.marvel.database.DatabaseEvent
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterDataWrapper(
    val code : Int?, // The HTTP status code of the returned result
    val status : String?, // A String? description of the call status
    val copyright : String?, // The copyright notice for the returned result
    val attributionText : String?, //The attribution notice for this result.
    // Please display either this notice or the contents of the attributionHTML field on
    // all screens which contain data from the Marvel Comics API.
    val attributionHTML : String?, //An HTML representation of the attribution notice for this result
    // Please display either this notice or the contents of the attributionText field on all screens
    // which contain data from the Marvel Comics API
    val data : CharacterDataContainer?, //The results returned by the call
    val etag : String? //A digest value of the content returned by the call
)

@JsonClass(generateAdapter = true)
data class CharacterDataContainer(
    val offset : Int?, // The requested offset (number of skipped results) of the call
    val limit : Int?, // The requested result limit
    val total : Int?, // The total number of resources available given the current filter set
    val count : Int?, // The total number of results returned by this call
    val results : List<NetworkCharacter?>?// The list of characters returned by the call
)

@JsonClass(generateAdapter = true)
data class NetworkCharacter(
    val id : Int?,   //The unique ID of the character resource
    val name : String?, //The name of the character
    val description : String?, // A short bio or description of the character
    val modified : String?, //The String the resource was most recently modified
    val resourceURI : String?, //The canonical URL identifier for this resource
    val urls : List<Url?>?, //A set of public web site URLs for the resource
    val thumbnail : Image?, //The representative image for this character
    val comics : ComicList?, //A resource list containing comics which feature this character
    val stories : StoryList?, //A resource list of stories in which this character appears
    val events : EventList?, // A resource list of events in which this character appears
    val series : SeriesList? //A resource list of series in which this character appears
)

@JsonClass(generateAdapter = true)
data class Url(
    val type : String?, // A text identifier for the URL
    val url : String? //A full URL : Including scheme, domain, and path)
)

@JsonClass(generateAdapter = true)
data class Image(
    val path : String?, //The directory path of to the image
    val extension : String? //The file extension for the image
)

@JsonClass(generateAdapter = true)
data class ComicList(
    val available : Int?, // The number of total available issues in this list
    // Will always be greater than or equal to the "returned" value
    val returned : Int?,  // The number of issues returned in this collection (up to 20)
    val collectionURI : String?, // The path to the full list of issues in this collection
    val items : List<ComicSummary?>? //The list of returned issues in this collection
)

@JsonClass(generateAdapter = true)
data class ComicSummary(
    val resourceURI : String?,  // The path to the individual comic resource
    val name : String? // The canonical name of the comic
)

@JsonClass(generateAdapter = true)
data class StoryList(
    val available : Int?, // The number of total available stories in this list
    // Will always be greater than or equal to the "returned" value
    val returned : Int?, // The number of stories returned in this collection (up to 20)
    val collectionURI : String?, // The path to the full list of stories in this collection
    val items : List<StorySummary?>? // The list of returned stories in this collection
)

@JsonClass(generateAdapter = true)
data class StorySummary(
    val resourceURI : String?, // The path to the individual story resource
    val name : String?,  // The canonical name of the story
    val type : String? // The type of the story : Interior or cover)
)

@JsonClass(generateAdapter = true)
data class EventList(
    val available : Int?, // The number of total available events in this list
    // Will always be greater than or equal to the "returned" value
    val returned : Int?, //The number of events returned in this collection (up to 20)
    val collectionURI : String?, // The path to the full list of events in this collection
    val items : List<EventSummary?>? //The list of returned events in this collection.
)

@JsonClass(generateAdapter = true)
data class EventSummary(
    val resourceURI : String?, // The path to the individual event resource
    val name : String? // The name of the event.
)

@JsonClass(generateAdapter = true)
data class SeriesList(
    val available : Int?, //The number of total available series in this list
    // Will always be greater than or equal to the "returned" value
    val returned : Int?, // The number of series returned in this collection (up to 20)
    val collectionURI : String?, //The path to the full list of series in this collection
    val items : List<SeriesSummary?>? // The list of returned series in this collection
)

@JsonClass(generateAdapter = true)
data class SeriesSummary(
    val resourceURI : String?, // The path to the individual series resource.,
    val name : String? //The canonical name of the series
)

@JsonClass(generateAdapter = true)
data class ComicDataWrapper(
    val code : Int?, // The HTTP status code of the returned result
    val status : String?, // A String? description of the call status
    val copyright : String?, // The copyright notice for the returned result
    val attributionText : String?, // The attribution notice for this result
    // Please display either this notice or the contents of the attributionHTML field on all
    // screens which contain data from the Marvel Comics API
    val attributionHTML : String?, // An HTML representation of the attribution notice for this result.
    // Please display either this notice or the contents of the attributionText field on all
    // screens which contain data from the Marvel Comics API
    val data : ComicDataContainer?, // The results returned by the call
    val etag : String? // A digest value of the content returned by the call
)

@JsonClass(generateAdapter = true)
data class ComicDataContainer(
    val offset : Int?, // The requested offset (number of skipped results) of the call
    val limit : Int?, // The requested result limit
    val total : Int?, // The total number of resources available given the current filter set
    val count : Int?, // The total number of results returned by this call
    val results : List<NetworkComic?>? // The list of comics returned by the call
)

@JsonClass(generateAdapter = true)
data class NetworkComic(
    val id : Int?, // The unique ID of the comic resource
    val digitalId : Int?,  // The ID of the digital comic representation of this comic
    // Will be 0 if the comic is not available digitally
    val title : String?,  // The canonical title of the comic
    val issueNumber : Double?,  // The number of the issue in the series (will generally be 0 for collection formats)
    val variantDescription : String?,  // If the issue is a variant
    // (e.g. an alternate cover, second prInt?ing, or director’s cut), a text description of the variant
    val description : String?,  // The preferred description of the comic
    val modified : String,  // The String the resource was most recently modified
    val isbn : String?,  // The ISBN for the comic (generally only populated for collection formats)
    val upc : String?,  // The UPC barcode number for the comic (generally only populated for periodical formats)
    val diamondCode : String?, // The Diamond code for the comic
    val ean : String?,  // The EAN barcode for the comic
    val issn : String?,  // The ISSN barcode for the comic
    val format : String?,  // The publication format of the comic e.g. comic, hardcover, trade paperback
    val pageCount : Int?,  // The number of story pages in the comic
    val textObjects : List<TextObject?>?, // A set of descriptive text blurbs for the comic
    val resourceURI : String?,  // The canonical URL identifier for this resource
    val urls : List<Url?>?, // A set of public web site URLs for the resource
    val series : SeriesSummary?, // A summary representation of the series to which this comic belongs
    val variants : List<ComicSummary?>?,  // A list of variant issues for this comic
    // (Includes the "original" issue if the current issue is a variant)
    val collections : List<ComicSummary?>?,  // A list of collections which include this comic
    // (will generally be empty if the comic's format is a collection)
    val collectedIssues : List<ComicSummary?>?,  // A list of issues collected in this comic
    // (will generally be empty for periodical formats such as "comic" or "magazine")
    val Strings : List<ComicString?>?, // A list of key Strings for this comic
    val prices : List<ComicPrice?>?, // A list of prices for this comic
    val thumbnail : Image?,  // The representative image for this comic
    val images : List<Image?>?, // A list of promotional images associated with this comic
    val creators : CreatorList?, // A resource list containing the creators associated with this comic
    val characters : CharacterList?, // A resource list containing the characters which appear in this comic
    val stories : StoryList?, // A resource list containing the stories which appear in this comic
    val events : EventList? // A resource list containing the events in which this comic appears
)

@JsonClass(generateAdapter = true)
data class TextObject(
    val type : String?, // The canonical type of the text object (e.g. solicit text, preview text, etc.)
    val language : String?, // The IETF language tag denoting the language the text object is written in
    val text : String? // The text.
)

@JsonClass(generateAdapter = true)
data class ComicString(
    val type : String?, // A description of the String (e.g. on sale String, FOC String)
    val String : String? // The String.
)

@JsonClass(generateAdapter = true)
data class ComicPrice(
    val type : String?, // A description of the price (e.g. prInt? price, digital price)
    val price : Float? // The price (all prices in USD).
)

@JsonClass(generateAdapter = true)
data class CreatorList(
    val available : Int?,  // The number of total available creators in this list
    // Will always be greater than or equal to the "returned" value
    val returned : Int?,  // The number of creators returned in this collection (up to 20)
    val collectionURI : String?,  // The path to the full list of creators in this collection
    val items : List<CreatorSummary?>?  // The list of returned creators in this collection
)

@JsonClass(generateAdapter = true)
data class CreatorSummary(
    val resourceURI : String?,  // The path to the individual creator resource
    val name : String?,  // The full name of the creator
    val role : String? // The role of the creator in the parent entity
)

@JsonClass(generateAdapter = true)
data class CharacterList(
    val available : Int?,  // The number of total available characters in this list
    // Will always be greater than or equal to the "returned" value
    val returned : Int?,  // The number of characters returned in this collection (up to 20)
    val collectionURI : String?,  // The path to the full list of characters in this collection
    val items : List<CharacterSummary?>?  // The list of returned characters in this collection
)

@JsonClass(generateAdapter = true)
data class CharacterSummary(
    val resourceURI : String?,  // The path to the individual character resource
    val name : String?,  // The full name of the character
    val role : String?  // The role of the creator in the parent entity
)

@JsonClass(generateAdapter = true)
data class EventDataWrapper(
    val code : Int?,  // The HTTP status code of the returned result
    val status : String?, // A string description of the call status
    val copyright : String?, // The copyright notice for the returned result
    val attributionText : String?, // The attribution notice for this result.
    // Please display either this notice or the contents of the attributionHTML field on all screens
    // which contain data from the Marvel Comics API
    val attributionHTML : String?, // An HTML representation of the attribution notice for this result.
    // Please display either this notice or the contents of the attributionText field on all screens
    // which contain data from the Marvel Comics API
    val data : EventDataContainer?, // The results returned by the call
    val etag : String? // A digest value of the content returned by the call
)

@JsonClass(generateAdapter = true)
data class EventDataContainer(
    val offset : Int?, // The requested offset (number of skipped results) of the call
    val limit : Int?, // The requested result limit
    val total : Int?, // The total number of resources available given the current filter set
    val count : Int?, // The total number of results returned by this call
    val results : List<NetworkEvent?>? // The list of events returned by the call
)

@JsonClass(generateAdapter = true)
data class NetworkEvent(
    val id : Int?, // The unique ID of the event resource
    val title : String?, // The title of the event
    val description : String?, // A description of the event
    val resourceURI : String?, // The canonical URL identifier for this resource
    val urls : List<Url?>?, // A set of public web site URLs for the event
    val modified : String?, // The date the resource was most recently modified
    val start : String?, // The date of publication of the first issue in this event
    val end : String?, // The date of publication of the last issue in this event
    val thumbnail : Image?, // The representative image for this event
    val comics : ComicList?, // A resource list containing the comics in this event
    val stories : StoryList?, // A resource list containing the stories in this event
    val series : SeriesList?, // A resource list containing the series in this event
    val characters : CharacterList?, // A resource list containing the characters which appear in this event
    val creators : CreatorList?, // A resource list containing creators whose work appears in this event
    val next : EventSummary?, // A summary representation of the event which follows this event
    val previous : EventSummary? // A summary representation of the event which preceded this event
)

fun ComicDataWrapper.asDatabaseComic() : Array<DatabaseComic?>? {
    return data?.results?.map { networkComic ->
        networkComic?.let {
            DatabaseComic(
                id = networkComic.id,
                title = networkComic.title,
                issueNumber = networkComic.issueNumber,
                variantDescription = networkComic.variantDescription,
                pageCount = networkComic.pageCount,
                thumbnail = networkComic.thumbnail?.imgSrcUrl(),
                description = networkComic.description,
                url = networkComic.urls?.elementAt(0)?.url
            )
        }
    }?.toTypedArray()
}

fun Image.imgSrcUrl() : String? {
    return "$path.$extension"
}

fun CharacterDataWrapper.asDatabaseCharacter() : Array<DatabaseCharacter?>? {
    return data?.results?.map { networkCharacter->
        networkCharacter?.let {
            DatabaseCharacter(
                id = networkCharacter.id,
                name = networkCharacter.name,
                description = networkCharacter.description,
                thumbnail = networkCharacter.thumbnail?.imgSrcUrl(),
                url = networkCharacter.urls?.elementAt(0)?.url
            )
        }
    }?.toTypedArray()
}

fun EventDataWrapper.asDatabaseEvent() : Array<DatabaseEvent?>? {
    return data?.results?.map {networkEvent ->
        networkEvent?.let {
            DatabaseEvent(
                id = networkEvent.id,
                title = networkEvent.title,
                description = networkEvent.description,
                thumbnail = networkEvent.thumbnail?.imgSrcUrl(),
                url = networkEvent.urls?.elementAt(0)?.url
            )
        }
    }?.toTypedArray()
}

