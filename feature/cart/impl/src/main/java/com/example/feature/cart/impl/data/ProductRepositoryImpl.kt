package com.example.feature.cart.impl.data

import com.example.core.db.dao.ProductDao
import com.example.feature.cart.api.model.ProductInfo
import com.example.feature.cart.api.repository.ProductRepository
import com.example.feature.cart.impl.data.mapper.toProduct
import com.example.feature.cart.impl.data.mapper.toProductInfo
import com.example.feature.cart.impl.data.mapper.toProductInfoList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductRepositoryImpl(
    private val productDao: ProductDao,
    private val dispatcherIO: CoroutineDispatcher = Dispatchers.IO,
) : ProductRepository {
    override suspend fun deleteAllProducts() =
        withContext(dispatcherIO) { productDao.deleteAll() }

    override suspend fun deleteProduct(productInfo: ProductInfo) =
        withContext(dispatcherIO) { productDao.delete(productInfo.toProduct()) }

    override suspend fun getAllProducts(): List<ProductInfo> =
        withContext(dispatcherIO) { productDao.getAll().toProductInfoList() }

    override suspend fun saveProduct(productInfo: ProductInfo) =
        withContext(dispatcherIO) { productDao.save(productInfo.toProduct()) }

    override suspend fun getProductById(id: Long): ProductInfo =
        withContext(dispatcherIO) { productDao.getProductById(id).toProductInfo() }

    override suspend fun getProductByTitle(title: String): ProductInfo? =
        withContext(dispatcherIO) { productDao.getProductByTitle(title)?.toProductInfo() }

    override suspend fun updateProduct(productInfo: ProductInfo) =
        withContext(dispatcherIO) { productDao.update(productInfo.toProduct()) }

    override suspend fun summaryPrice(): String =
        withContext(dispatcherIO) { productDao.summaryPrice().toString() }

}
