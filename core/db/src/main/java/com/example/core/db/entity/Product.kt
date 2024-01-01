package com.example.core.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val title: String,
    val imageUrl: String,
    val price: String,
    val count: Int,
)
