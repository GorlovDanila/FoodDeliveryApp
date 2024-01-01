package com.example.core.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.example.core.db.entity.Product

@Dao
interface ProductDao {

    @Insert(onConflict = REPLACE)
    fun save(product: Product)

    @Delete
    fun delete(product: Product)

    @Query("DELETE FROM products")
    fun deleteAll()

    @Query("SELECT * FROM products")
    fun getAll(): List<Product>

    @Query("SELECT * FROM products WHERE id = :id")
    fun getProductById(id: Long): Product

    @Query("SELECT * FROM products WHERE title = :title")
    fun getProductByTitle(title: String): Product?

    @Update(onConflict = REPLACE)
    fun update(product: Product)

    @Query("SELECT SUM(price * count) as sum FROM products")
    fun summaryPrice() : Long
}
