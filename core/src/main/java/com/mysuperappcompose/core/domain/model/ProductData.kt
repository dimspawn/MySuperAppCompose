package com.mysuperappcompose.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductData(
    val productId: Int,
    val description: String,
    val price: Int,
    val thumbnail: String,
    var title: String,
    var search: Boolean,
    var cart: Boolean
): Parcelable