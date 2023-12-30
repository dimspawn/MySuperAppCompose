package com.mysuperappcompose.core.data.source.remote.response

import com.google.gson.annotations.SerializedName


data class ShopProductResponse(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("price")
    val price: Int? = null,
    @SerializedName("thumbnail")
    val thumbnail: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("images")
    val images: List<String>? = null,
    @SerializedName("search")
    val search: Boolean? = null
)