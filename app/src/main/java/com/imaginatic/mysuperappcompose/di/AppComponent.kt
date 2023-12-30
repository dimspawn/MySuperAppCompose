package com.imaginatic.mysuperappcompose.di

import com.imaginatic.mysuperappcompose.ui.MainActivity
import com.mysuperappcompose.core.di.CoreComponent
import com.mysuperappcompose.core.domain.usecase.ShopUseCase
import dagger.Component

@AppScope
@Component(
    modules = [ViewModelModule::class], dependencies = [CoreComponent::class]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        fun coreComponent(coreComponent: CoreComponent): Builder
        fun build(): AppComponent
    }

    fun provideShopUseCase(): ShopUseCase

    fun inject(activity: MainActivity)
}