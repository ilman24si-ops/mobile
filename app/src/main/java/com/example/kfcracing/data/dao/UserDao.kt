package com.example.kfcracing.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.kfcracing.data.entity.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE username = :username LIMIT 1")
    suspend fun getUserByUsername(username: String): UserEntity?

    @Insert
    suspend fun insert(user: UserEntity)

    @Query("SELECT COUNT(*) FROM users")
    suspend fun getUserCount(): Int
}
