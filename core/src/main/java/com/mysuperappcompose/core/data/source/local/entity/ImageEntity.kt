package com.mysuperappcompose.core.data.source.local.entity

import android.os.Parcelable
import androidx.room.*
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "images")
data class ImageEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "imageId")
    var imageId: Int = 0,

    @ColumnInfo(name = "productId")
    var productId: Int,

    @ColumnInfo(name = "imageUrl")
    var imageUrl: String
): Parcelable