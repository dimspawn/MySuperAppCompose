package com.mysuperappcompose.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mysuperappcompose.core.data.source.local.entity.ImageEntity
import com.mysuperappcompose.core.data.source.local.entity.ProductEntity

@Database(entities = [ProductEntity::class, ImageEntity::class], version = 2, exportSchema = false)
abstract class ShopDatabase: RoomDatabase() {
    abstract fun shopDao(): ProductDao
}