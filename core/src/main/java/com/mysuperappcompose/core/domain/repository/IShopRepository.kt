package com.mysuperappcompose.core.domain.repository

import com.mysuperappcompose.core.data.Resource
import com.mysuperappcompose.core.domain.model.ImageData
import com.mysuperappcompose.core.domain.model.ProductData
import kotlinx.coroutines.flow.Flow

interface IShopRepository {
    fun getProducts(): Flow<Resource<List<ProductData>>>
    fun getProductById(id: Int): Flow<Resource<List<ProductData>>>
    fun searchProduct(query: String): Flow<Resource<List<ProductData>>>
    fun getImagesProduct(id: Int): Flow<Resource<List<ImageData>>>
    fun getCartProducts(): Flow<Resource<List<ProductData>>>
    fun setCartProduct(id: Int, cart: Boolean)
}