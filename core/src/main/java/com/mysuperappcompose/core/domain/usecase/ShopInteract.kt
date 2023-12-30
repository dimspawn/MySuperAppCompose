package com.mysuperappcompose.core.domain.usecase

import com.mysuperappcompose.core.data.Resource
import com.mysuperappcompose.core.data.ShopRepository
import com.mysuperappcompose.core.domain.model.ImageData
import com.mysuperappcompose.core.domain.model.ProductData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ShopInteract @Inject constructor(private val shopRepository: ShopRepository): ShopUseCase{
    override fun getProducts(): Flow<Resource<List<ProductData>>> = shopRepository.getProducts()
    override fun getProductById(id: Int): Flow<Resource<List<ProductData>>> = shopRepository.getProductById(id)
    override fun searchProduct(query: String): Flow<Resource<List<ProductData>>> = shopRepository.searchProduct(query)
    override fun getImagesProduct(id: Int): Flow<Resource<List<ImageData>>> = shopRepository.getImagesProduct(id)
    override fun getCartProducts(): Flow<Resource<List<ProductData>>> = shopRepository.getCartProducts()
    override fun setCartProduct(id: Int, cart: Boolean) { shopRepository.setCartProduct(id, cart) }
}