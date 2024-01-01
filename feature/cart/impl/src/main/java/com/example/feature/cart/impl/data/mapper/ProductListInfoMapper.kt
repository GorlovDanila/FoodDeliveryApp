package com.example.feature.cart.impl.data.mapper

import com.example.core.db.entity.Product
import com.example.feature.cart.api.model.ProductInfo

fun List<Product>.toProductInfoList() : List<ProductInfo> = map { it.toProductInfo() }
