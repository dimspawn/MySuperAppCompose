package com.imaginatic.mysuperappcompose.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.imaginatic.mysuperappcompose.ui.navigation.model.BottomBarScreen
import com.imaginatic.mysuperappcompose.ui.screen.cart.CartViewModel
import com.imaginatic.mysuperappcompose.ui.screen.detail.DetailViewModel
import com.imaginatic.mysuperappcompose.ui.screen.home.HomeViewModel

@Composable
fun BottomNav(
    homeViewModel: HomeViewModel,
    detailViewModel: DetailViewModel,
    cartViewModel: CartViewModel,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.primary,
        bottomBar = {
            if (currentRoute == BottomBarScreen.Home.route ||
                currentRoute == BottomBarScreen.Cart.route ||
                currentRoute == BottomBarScreen.Profile.route
                ) {
                BottomBar(navController = navController)
            }
        }
    ) {
        MainNavHost(
            homeViewModel = homeViewModel,
            detailViewModel = detailViewModel,
            cartViewModel = cartViewModel,
            navController = navController,
            innerPadding = it
        )
    }
}

@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Cart,
        BottomBarScreen.Profile
    )

    val navStackBackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navStackBackEntry?.destination

    Row(
        modifier = modifier
            .padding(start = 0.dp, end = 0.dp, top = 10.dp, bottom = 10.dp)
            .background(Color.Transparent)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        screens.forEach { screen ->
            BottomNavItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}