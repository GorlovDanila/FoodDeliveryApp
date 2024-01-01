package com.example.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.db.dao.ProductDao
import com.example.core.db.entity.Product

@Database(entities = [Product::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getProductDao() : ProductDao
}
