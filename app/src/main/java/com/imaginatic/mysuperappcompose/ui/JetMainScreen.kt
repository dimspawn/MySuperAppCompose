package com.imaginatic.mysuperappcompose.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.imaginatic.mysuperappcompose.ui.navigation.BottomNav
import com.imaginatic.mysuperappcompose.ui.screen.cart.CartViewModel
import com.imaginatic.mysuperappcompose.ui.screen.detail.DetailViewModel
import com.imaginatic.mysuperappcompose.ui.screen.home.HomeViewModel

@Composable
fun JetMainScreen(
    homeViewModel: HomeViewModel,
    detailViewModel: DetailViewModel,
    cartViewModel: CartViewModel,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    BottomNav(
        homeViewModel,
        detailViewModel,
        cartViewModel,
        modifier, navController
    )
}