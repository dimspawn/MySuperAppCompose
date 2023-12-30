package com.mysuperappcompose.core.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "shop")
data class ProductEntity(
    @PrimaryKey
    @ColumnInfo(name = "productId")
    var productId: Int,
    @ColumnInfo(name = "description")
    var description: String,
    @ColumnInfo(name = "price")
    var price: Int,
    @ColumnInfo(name = "thumbnail")
    var thumbnail: String,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "search")
    var search: Boolean,
    @ColumnInfo(name = "cart")
    var cart: Boolean
): Parcelable