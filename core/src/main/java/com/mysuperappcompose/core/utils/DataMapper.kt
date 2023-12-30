package com.mysuperappcompose.core.utils

import com.mysuperappcompose.core.data.source.local.entity.ImageEntity
import com.mysuperappcompose.core.data.source.local.entity.ProductEntity
import com.mysuperappcompose.core.data.source.remote.response.ShopProductResponse
import com.mysuperappcompose.core.domain.model.ImageData
import com.mysuperappcompose.core.domain.model.ProductData

object DataMapper {
    fun mapProductEntitiesToDomains(productEntities: List<ProductEntity>): List<ProductData> = productEntities.map {
        ProductData(
            productId = it.productId,
            description = it.description,
            price = it.price,
            thumbnail = it.thumbnail,
            title = it.title,
            search = it.search,
            cart = it.cart
        )
    }

    fun mapProductResponseToEntity(productResponse: ShopProductResponse, isSearch: Boolean = false): ProductEntity = ProductEntity(
        productId = productResponse.id ?: -1,
        description = productResponse.description ?: "[description]",
        price = productResponse.price ?: 0,
        thumbnail = productResponse.thumbnail ?: "[thumbnail]",
        title = productResponse.title ?: "[title]",
        search = isSearch,
        cart = false
    )

    fun mapImageEntitiesToDomains(imageEntities: List<ImageEntity>): List<ImageData> = imageEntities.map {
        ImageData(
            imageId = it.imageId,
            productId = it.productId,
            imageUrl = it.imageUrl
        )
    }
}