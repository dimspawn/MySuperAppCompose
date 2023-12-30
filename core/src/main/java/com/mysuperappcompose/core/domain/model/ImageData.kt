package com.mysuperappcompose.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImageData(
    val imageId: Int,
    val productId: Int,
    val imageUrl: String
): Parcelable