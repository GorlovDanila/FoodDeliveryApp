package com.example.feature.cart.api.repository

import com.example.feature.cart.api.model.ProductInfo

interface ProductRepository {

    suspend fun deleteAllProducts()

    suspend fun deleteProduct(productInfo: ProductInfo)

    suspend fun getAllProducts(): List<ProductInfo>

    suspend fun saveProduct(productInfo: ProductInfo)

    suspend fun getProductById(id: Long): ProductInfo

    suspend fun getProductByTitle(title: String): ProductInfo?

    suspend fun updateProduct(productInfo: ProductInfo)

    suspend fun summaryPrice() : String
}
