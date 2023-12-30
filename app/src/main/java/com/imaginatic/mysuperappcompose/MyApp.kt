package com.imaginatic.mysuperappcompose

import android.app.Application
import com.imaginatic.mysuperappcompose.di.AppComponent
import com.imaginatic.mysuperappcompose.di.DaggerAppComponent
import com.mysuperappcompose.core.di.DaggerCoreComponent

open class MyApp : Application() {
    private val coreComponent by lazy {
        DaggerCoreComponent.builder().context(this).build()
    }

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().coreComponent(coreComponent).build()
    }
}