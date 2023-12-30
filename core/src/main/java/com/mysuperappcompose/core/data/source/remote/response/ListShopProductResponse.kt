package com.mysuperappcompose.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListShopProductResponse(
    @SerializedName("limit")
    val limit: Int? = null,
    @SerializedName("skip")
    val skip: Int? = null,
    @SerializedName("total")
    val total: Int? = null,
    @SerializedName("products")
    val products: List<ShopProductResponse>? = null
)