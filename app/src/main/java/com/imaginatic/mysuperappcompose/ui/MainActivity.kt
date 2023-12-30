package com.imaginatic.mysuperappcompose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.imaginatic.mysuperappcompose.MyApp
import com.imaginatic.mysuperappcompose.ui.screen.cart.CartViewModel
import com.imaginatic.mysuperappcompose.ui.screen.detail.DetailViewModel
import com.imaginatic.mysuperappcompose.ui.screen.home.HomeViewModel
import com.mysuperappcompose.core.ui.theme.ShopeeTheme
import com.mysuperappcompose.core.ui.theme.StatusBarColor
import com.mysuperappcompose.core.ui.ViewModelFactory
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var factory: ViewModelFactory

    private val homeViewModel: HomeViewModel by viewModels { factory }
    private val detailViewModel: DetailViewModel by viewModels { factory }
    private val cartViewModel: CartViewModel by viewModels { factory }
    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApp).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContent {
            ShopeeTheme {
                StatusBarColor(color = MaterialTheme.colors.primary)
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    JetMainScreen(
                        homeViewModel = homeViewModel,
                        detailViewModel = detailViewModel,
                        cartViewModel = cartViewModel
                    )
                }
            }
        }
    }
}
