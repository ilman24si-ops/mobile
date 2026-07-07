package com.example.kfcracing.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.kfcracing.data.entity.PembangunanEntity

@Dao
interface PembangunanDao {
    @Query("SELECT * FROM pembangunan")
    suspend fun getAll(): List<PembangunanEntity>

    @Insert
    suspend fun insert(pembangunan: PembangunanEntity)

    @Delete
    suspend fun delete(pembangunan: PembangunanEntity)
}
