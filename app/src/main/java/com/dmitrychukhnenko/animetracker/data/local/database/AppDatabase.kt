package com.dmitrychukhnenko.animetracker.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dmitrychukhnenko.animetracker.data.local.dao.TitleDao
import com.dmitrychukhnenko.animetracker.data.local.entity.TitleEntity

@Database(entities = [TitleEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun titleDao(): TitleDao
}