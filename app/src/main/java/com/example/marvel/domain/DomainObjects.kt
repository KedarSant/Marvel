package com.example.marvel.domain


data class Character(
    val id : Int?,   //The unique ID of the character resource
    val name : String?, //The name of the character
    val description : String?, // A short bio or description of the character
    val thumbnail : String?, //The representative image for this character
    val url : String?
)

data class Comic(
    val id : Int?, // The unique ID of the comic resource
    val title : String?,  // The canonical title of the comic
    val issueNumber : Double?,  // The number of the issue in the series (will generally be 0 for collection formats)
    val variantDescription : String?,  // If the issue is a variant
    val pageCount : Int?,  // The number of story pages in the comic
    val thumbnail : String?,  // The representative image for this comic
    val description : String?, // The preferred description of the comic
    val url : String?
)

data class Event(
    val id : Int?, // The unique ID of the event resource
    val title : String?, // The title of the event
    val description : String?, // A description of the event
    val thumbnail : String?, // The representative image for this event
    val url : String?
)
