package com.example.kfcracing.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kfcracing.data.dao.PembangunanDao
import com.example.kfcracing.data.dao.UserDao
import com.example.kfcracing.data.entity.PembangunanEntity
import com.example.kfcracing.data.entity.UserEntity

@Database(
    entities = [PembangunanEntity::class, UserEntity::class],
    version = 3
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun pembangunanDao(): PembangunanDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "bina_desa_db"
                )
                    .fallbackToDestructiveMigration()
                    .build().also { INSTANCE = it }
            }
        }
    }
}
