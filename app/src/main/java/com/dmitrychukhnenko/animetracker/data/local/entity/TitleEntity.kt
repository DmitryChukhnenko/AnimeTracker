package com.dmitrychukhnenko.animetracker.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "titles")
data class TitleEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val imageUrl: String,
    val synopsis: String?,
    val score: Float?,
    val genres: String,
    val addedDate: Long = System.currentTimeMillis()
)