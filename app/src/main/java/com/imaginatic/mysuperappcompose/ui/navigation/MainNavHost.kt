package com.imaginatic.mysuperappcompose.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.imaginatic.mysuperappcompose.ui.navigation.model.BottomBarScreen
import com.imaginatic.mysuperappcompose.ui.navigation.model.GeneralScreen
import com.imaginatic.mysuperappcompose.ui.screen.cart.CartScreen
import com.imaginatic.mysuperappcompose.ui.screen.cart.CartViewModel
import com.imaginatic.mysuperappcompose.ui.screen.detail.DetailScreen
import com.imaginatic.mysuperappcompose.ui.screen.detail.DetailViewModel
import com.imaginatic.mysuperappcompose.ui.screen.home.HomeScreen
import com.imaginatic.mysuperappcompose.ui.screen.home.HomeViewModel
import com.imaginatic.mysuperappcompose.ui.screen.profile.ProfileScreen
import com.imaginatic.mysuperappcompose.ui.screen.search.SearchScreen

@Composable
fun MainNavHost(
    homeViewModel: HomeViewModel,
    detailViewModel: DetailViewModel,
    cartViewModel: CartViewModel,
    navController: NavHostController,
    innerPadding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable(BottomBarScreen.Home.route) {
            HomeScreen(
                homeViewModel = homeViewModel,
                navigateToDetail = { productId ->
                    navController.navigate(GeneralScreen.DetailProduct.createRoute(productId))
                },
                navigateToSearch = {
                    navController.navigate(GeneralScreen.SearchProduct.route)
                }
            )
        }
        composable(BottomBarScreen.Cart.route) {
            CartScreen(
                cartViewModel = cartViewModel,
                navigateToDetail = { productId ->
                    navController.navigate(GeneralScreen.DetailProduct.createRoute(productId))
                }
            )
        }
        composable(BottomBarScreen.Profile.route) {
            ProfileScreen()
        }
        composable(
            route = GeneralScreen.DetailProduct.route,
            arguments = listOf(navArgument("productId") { type = NavType.IntType }),
        ) {
            val id = it.arguments?.getInt("productId") ?: -1
            DetailScreen(
                productId = id,
                detailViewModel = detailViewModel,
                navigateBack = {
                    navController.navigateUp()
                },
            )
        }
        composable(
            route = GeneralScreen.SearchProduct.route,
        ) {
            SearchScreen(
                homeViewModel = homeViewModel,
                navigateToDetail = { productId ->
                    navController.navigate(GeneralScreen.DetailProduct.createRoute(productId))
                },
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}