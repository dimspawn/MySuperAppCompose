package com.mysuperappcompose.core.di

import android.content.Context
import com.mysuperappcompose.core.domain.repository.IShopRepository
import com.mysuperappcompose.core.domain.usecase.ShopUseCase
import dagger.BindsInstance
import dagger.Component

@CoreScope
@Component(modules = [RepositoryModule::class])
interface CoreComponent {
    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun build(): CoreComponent
    }

    fun provideShopUseCase(): ShopUseCase
    fun provideRepository(): IShopRepository
}