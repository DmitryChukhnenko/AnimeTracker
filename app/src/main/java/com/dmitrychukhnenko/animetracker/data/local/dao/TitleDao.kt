package com.dmitrychukhnenko.animetracker.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dmitrychukhnenko.animetracker.data.local.entity.TitleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TitleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTitle(title: TitleEntity)

    @Delete
    suspend fun deleteTitle(title: TitleEntity)

    @Query("SELECT * FROM titles ORDER BY addedDate DESC")
    fun getAllTitles(): Flow<List<TitleEntity>>

    @Query("SELECT * FROM titles WHERE id = :id")
    suspend fun getTitleById(id: Int): TitleEntity?
}