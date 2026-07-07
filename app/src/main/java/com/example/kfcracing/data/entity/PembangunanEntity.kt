package com.example.kfcracing.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pembangunan")
data class PembangunanEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val namaProyek: String,
    val lokasi: String,
    val anggaran: Double,
    val status: String,
    val imagePath: String? = null,
    val tanggalMulai: Long
)
