package com.yogigupta1206.dog_library.data.data_source.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yogigupta1206.dog_library.domain.model.Image

@Dao
interface DogsDao {

    @Query("SELECT * FROM dogs_image WHERE id = :i")
    suspend fun getImageFromIndex(i:Int): Image?

    @Query("SELECT COUNT(id) FROM dogs_image")
    suspend fun getImageCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImage(image: Image)

    @Query("SELECT * FROM dogs_image ORDER BY id DESC LIMIT 1")
    suspend fun getLastImage(): Image

}