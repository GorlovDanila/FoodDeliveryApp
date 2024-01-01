package com.example.feature.cart.impl.data.mapper

import com.example.core.db.entity.Product
import com.example.feature.cart.api.model.ProductInfo

fun Product.toProductInfo() : ProductInfo = ProductInfo(
    id = id,
    title = title,
    imageUrl = imageUrl,
    price = price,
    count = count
)

fun ProductInfo.toProduct() : Product = Product(
    id = id,
    title = title,
    imageUrl = imageUrl,
    price = price,
    count = count
)
