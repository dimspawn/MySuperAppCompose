package com.mysuperappcompose.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mysuperappcompose.core.data.source.local.entity.ImageEntity
import com.mysuperappcompose.core.data.source.local.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Query("SELECT * FROM shop")
    fun getShopProducts(): Flow<List<ProductEntity>>

    @Query("SELECT * FROM shop WHERE productId=:id")
    fun getShopProductById(id: Int): Flow<List<ProductEntity>>

    @Query("SELECT * FROM shop WHERE search=:search")
    fun getSearchShopProductByQuery(search: Boolean): Flow<List<ProductEntity>>

    @Query("SELECT * FROM images WHERE productId=:id")
    fun getImagesById(id: Int): Flow<List<ImageEntity>>

    @Query("SELECT * FROM shop WHERE cart = 1")
    fun getCartProducts(): Flow<List<ProductEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShopProduct(products: List<ProductEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShopImages(images: List<ImageEntity>)

    @Query("UPDATE shop SET search = :search")
    fun updateSearchShopProduct(search: Boolean)

    @Query("UPDATE shop SET cart = :cart WHERE productId=:id")
    fun updateCartProduct(id: Int, cart: Boolean)
}