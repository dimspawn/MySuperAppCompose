package com.imaginatic.mysuperappcompose.ui.navigation.model

sealed class GeneralScreen(val route: String) {
    object DetailProduct: BottomBarScreen("home/{productId}") {
        fun createRoute(productId: Int) = "home/$productId"
    }

    object SearchProduct: GeneralScreen(route = "home/search")
}