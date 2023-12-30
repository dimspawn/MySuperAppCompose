package com.mysuperappcompose.core.data.source.remote

import android.util.Log
import com.mysuperappcompose.core.data.source.remote.network.ApiResponse
import com.mysuperappcompose.core.data.source.remote.network.ApiService
import com.mysuperappcompose.core.data.source.remote.response.ListShopProductResponse
import com.mysuperappcompose.core.data.source.remote.response.ShopProductResponse
import com.mysuperappcompose.core.di.CoreScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@CoreScope
class RemoteDataSource @Inject constructor(private val apiService: ApiService){
    suspend fun getProducts(): Flow<ApiResponse<ListShopProductResponse>> {
        return flow {
            try {
                val response = apiService.getProducts()
                val shopList = response.products
                if (!shopList.isNullOrEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getProductById(id: Int): Flow<ApiResponse<ShopProductResponse>> {
        return flow {
            try {
                val response = apiService.getProductById(id)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun searchProduct(query: String): Flow<ApiResponse<ListShopProductResponse>> {
        return flow {
            try {
                val response = apiService.searchProduct(query)
                val shopList = response.products
                if(!shopList.isNullOrEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}