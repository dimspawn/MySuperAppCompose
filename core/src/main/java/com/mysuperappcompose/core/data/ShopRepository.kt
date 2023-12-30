package com.mysuperappcompose.core.data

import com.mysuperappcompose.core.data.source.local.LocalDataSource
import com.mysuperappcompose.core.data.source.local.entity.ImageEntity
import com.mysuperappcompose.core.data.source.local.entity.ProductEntity
import com.mysuperappcompose.core.data.source.remote.RemoteDataSource
import com.mysuperappcompose.core.data.source.remote.network.ApiResponse
import com.mysuperappcompose.core.data.source.remote.response.ListShopProductResponse
import com.mysuperappcompose.core.data.source.remote.response.ShopProductResponse
import com.mysuperappcompose.core.di.CoreScope
import com.mysuperappcompose.core.domain.model.ImageData
import com.mysuperappcompose.core.domain.model.ProductData
import com.mysuperappcompose.core.domain.repository.IShopRepository
import com.mysuperappcompose.core.utils.AppExecutors
import com.mysuperappcompose.core.utils.DataMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@CoreScope
class ShopRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): IShopRepository{
    override fun getProducts(): Flow<Resource<List<ProductData>>> =
        object : NetworkBoundResource<List<ProductData>, ListShopProductResponse>() {
            override fun loadFromDbFirst(): Flow<List<ProductData>> {
                return localDataSource.getShopProducts().map { DataMapper.mapProductEntitiesToDomains(it) }
            }

            override fun loadFromDb(): Flow<List<ProductData>> {
                return localDataSource.getShopProducts().map { DataMapper.mapProductEntitiesToDomains(it) }
            }

            override fun shouldFetch(data: List<ProductData>?) = data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<ListShopProductResponse>> = remoteDataSource.getProducts()

            override suspend fun saveCallResult(data: ListShopProductResponse) {
                val productList = data.products ?: emptyList()
                if (productList.isNotEmpty()) {
                    val insertProductList = ArrayList<ProductEntity>()
                    val imageResponseList = ArrayList<ImageEntity>()
                    for(productResponse in productList) {
                        val newProductEntity = DataMapper.mapProductResponseToEntity(productResponse)
                        insertProductList.add(newProductEntity)
                        if (!productResponse.images.isNullOrEmpty()) {
                            for (imageResponse in productResponse.images) {
                                val newImage = ImageEntity(productId = productResponse.id ?: -1, imageUrl = imageResponse)
                                imageResponseList.add(newImage)
                            }
                        }
                    }
                    localDataSource.insertShopProduct(insertProductList)
                    localDataSource.insertShopImages(imageResponseList)
                }
            }
        }.asFlow()

    override fun getProductById(id: Int): Flow<Resource<List<ProductData>>> =
        object : NetworkBoundResource<List<ProductData>, ShopProductResponse> () {
            override fun loadFromDbFirst(): Flow<List<ProductData>> {
                return localDataSource.getShopProductById(id).map { DataMapper.mapProductEntitiesToDomains(it) }
            }

            override fun loadFromDb(): Flow<List<ProductData>> {
                return localDataSource.getShopProductById(id).map { DataMapper.mapProductEntitiesToDomains(it) }
            }

            override fun shouldFetch(data: List<ProductData>?): Boolean = data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<ShopProductResponse>> = remoteDataSource.getProductById(id)

            override suspend fun saveCallResult(data: ShopProductResponse) {
                val insertProductList = ArrayList<ProductEntity>()
                val productEntity = DataMapper.mapProductResponseToEntity(data)
                insertProductList.add(productEntity)
                localDataSource.insertShopProduct(insertProductList)

                if(!data.images.isNullOrEmpty()) {
                    val imageList = ArrayList<ImageEntity>()
                    for (imageData in data.images) {
                        val newImage = ImageEntity(productId = data.id ?: -1, imageUrl = imageData)
                        imageList.add(newImage)
                    }
                    localDataSource.insertShopImages(imageList)
                }
            }
        }.asFlow()

    override fun searchProduct(query: String): Flow<Resource<List<ProductData>>> =
        object : NetworkBoundResource<List<ProductData>, ListShopProductResponse>() {
            override fun loadFromDbFirst(): Flow<List<ProductData>> {
                return localDataSource.getSearchShopProductByQuery(true).map { DataMapper.mapProductEntitiesToDomains(it) }
            }

            override fun loadFromDb(): Flow<List<ProductData>> {
                return localDataSource.getSearchShopProductByQuery(true).map { DataMapper.mapProductEntitiesToDomains(it) }
            }

            override fun shouldFetch(data: List<ProductData>?): Boolean {
                if (data != null) {
                    if (data.isNotEmpty()) {
                        appExecutors.diskIO().execute {
                            localDataSource.updateSearchShopProduct(false)
                        }
                    }
                }
                return query != ""
            }

            override suspend fun createCall(): Flow<ApiResponse<ListShopProductResponse>> = remoteDataSource.searchProduct(query)

            override suspend fun saveCallResult(data: ListShopProductResponse) {
                if (!data.products.isNullOrEmpty() && data.products.isNotEmpty()) {
                    val insertProductList = ArrayList<ProductEntity>()
                    for(productResponse in data.products) {
                        val newProductEntity = DataMapper.mapProductResponseToEntity(productResponse, true)
                        insertProductList.add(newProductEntity)
                    }
                    localDataSource.insertShopProduct(insertProductList)
                }
            }
        }.asFlow()

    override fun getImagesProduct(id: Int): Flow<Resource<List<ImageData>>> {
        return flow {
            emit(Resource.Loading())
            try {
                val data = localDataSource.getImagesById(id).map { DataMapper.mapImageEntitiesToDomains(it) }
                emit(Resource.Success(data.first()))
            } catch (e: Exception) {
                emit(Resource.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun getCartProducts(): Flow<Resource<List<ProductData>>> {
        return flow {
            emit(Resource.Loading())
            try {
                val data = localDataSource.getCartProducts().map { DataMapper.mapProductEntitiesToDomains(it) }
                emit(Resource.Success(data.first()))
            } catch (e: Exception) {
                emit(Resource.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun setCartProduct(id: Int, cart: Boolean) {
        appExecutors.diskIO().execute { localDataSource.updateCartProduct(id, cart) }
    }
}