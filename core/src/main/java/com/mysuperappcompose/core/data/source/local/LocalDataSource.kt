package com.mysuperappcompose.core.data.source.local

import com.mysuperappcompose.core.data.source.local.entity.ImageEntity
import com.mysuperappcompose.core.data.source.local.entity.ProductEntity
import com.mysuperappcompose.core.data.source.local.room.ProductDao
import com.mysuperappcompose.core.di.CoreScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@CoreScope
class LocalDataSource @Inject constructor(private val productDao: ProductDao){
    fun getShopProducts(): Flow<List<ProductEntity>> = productDao.getShopProducts()
    fun getShopProductById(id: Int): Flow<List<ProductEntity>> = productDao.getShopProductById(id)
    fun getSearchShopProductByQuery(search: Boolean): Flow<List<ProductEntity>> = productDao.getSearchShopProductByQuery(search)
    fun getImagesById(id: Int): Flow<List<ImageEntity>> = productDao.getImagesById(id)
    fun getCartProducts(): Flow<List<ProductEntity>> = productDao.getCartProducts()
    fun updateSearchShopProduct(search: Boolean) { productDao.updateSearchShopProduct(search) }
    suspend fun insertShopProduct(products: List<ProductEntity>) { productDao.insertShopProduct(products) }
    suspend fun insertShopImages(images: List<ImageEntity>) { productDao.insertShopImages(images) }
    fun updateCartProduct(id: Int, cart: Boolean) { productDao.updateCartProduct(id, cart) }
}