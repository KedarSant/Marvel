package com.example.marvel.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface ComicsDao {

    @Query("select * from databasecomic")
    fun getComics() : LiveData<List<DatabaseComic>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertComics(vararg comic: DatabaseComic?)

}

@Dao
interface CharacterDao {

    @Query("select * from databasecharacter")
    fun getCharacters() : LiveData<List<DatabaseCharacter>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacters(vararg character: DatabaseCharacter?)

}

@Dao
interface EventsDao{

    @Query("select * from databaseevent")
    fun getEvents() : LiveData<List<DatabaseEvent>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEvents(vararg events: DatabaseEvent?)
}

@Database(entities = [DatabaseComic::class,DatabaseCharacter::class,DatabaseEvent::class],
    version = 2)
abstract class MarvelDatabase : RoomDatabase() {
    abstract val comicsDao: ComicsDao
    abstract val characterDao : CharacterDao
    abstract val eventsDao : EventsDao
}

private lateinit var INSTANCE: MarvelDatabase

fun getDatabase(context: Context): MarvelDatabase {
    synchronized(MarvelDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                MarvelDatabase::class.java,
                "Marvel").build()
        }
    }
    return INSTANCE
}