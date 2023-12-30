package com.mysuperappcompose.core.di

import android.content.Context
import androidx.room.Room
import com.mysuperappcompose.core.data.source.local.room.ProductDao
import com.mysuperappcompose.core.data.source.local.room.ShopDatabase
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {
    @CoreScope
    @Provides
    fun provideDatabase(context: Context): ShopDatabase {
        return Room.databaseBuilder(
            context,
            ShopDatabase::class.java, "Shop.db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideShopDao(database: ShopDatabase): ProductDao = database.shopDao()
}