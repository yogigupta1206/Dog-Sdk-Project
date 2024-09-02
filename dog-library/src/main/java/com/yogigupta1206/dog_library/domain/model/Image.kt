package com.yogigupta1206.dog_library.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dogs_image")
data class Image(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val url: String? = null
)
