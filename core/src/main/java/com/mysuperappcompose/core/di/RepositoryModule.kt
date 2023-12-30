package com.mysuperappcompose.core.di

import com.mysuperappcompose.core.data.ShopRepository
import com.mysuperappcompose.core.domain.repository.IShopRepository
import com.mysuperappcompose.core.domain.usecase.ShopInteract
import com.mysuperappcompose.core.domain.usecase.ShopUseCase
import dagger.Binds
import dagger.Module

@Module(includes = [NetworkModule::class, DatabaseModule::class])
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepository(shopRepository: ShopRepository): IShopRepository

    @Binds
    abstract fun provideShopUseCase(shopInteract: ShopInteract): ShopUseCase
}