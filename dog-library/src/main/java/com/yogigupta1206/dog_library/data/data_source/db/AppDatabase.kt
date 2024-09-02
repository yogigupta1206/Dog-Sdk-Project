package com.yogigupta1206.dog_library.data.data_source.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yogigupta1206.dog_library.domain.model.Image

@Database(entities = [Image::class], version = 1)
abstract class AppDbDataSource : RoomDatabase() {

    abstract fun dogsDao(): DogsDao

    companion object {
        const val DATABASE_NAME = "dogs_db"
    }

}