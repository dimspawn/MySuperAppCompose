package com.mysuperappcompose.core.data.source.remote.network

import com.mysuperappcompose.core.data.source.remote.response.ListShopProductResponse
import com.mysuperappcompose.core.data.source.remote.response.ShopProductResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("products")
    suspend fun getProducts(): ListShopProductResponse

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Int): ShopProductResponse

    @GET("products/search")
    suspend fun searchProduct(@Query("q") query: String): ListShopProductResponse
}