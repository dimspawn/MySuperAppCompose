package com.imaginatic.mysuperappcompose.ui.screen.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.imaginatic.mysuperappcompose.ui.component.ProgressProduct
import com.imaginatic.mysuperappcompose.ui.screen.cart.section.CartContent
import com.mysuperappcompose.core.R
import com.mysuperappcompose.core.data.Resource
import com.mysuperappcompose.core.ui.theme.Gray200

@Composable
fun CartScreen(
    cartViewModel: CartViewModel,
    navigateToDetail: (Int) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.cart))
                },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White,
                elevation = 0.dp
            )
        },
        content = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Gray200)
                    .padding(it)
            ) {
                cartViewModel.setAllCarts(productId = 0)
                cartViewModel.cartProducts.collectAsState(initial = Resource.Loading()).value.let { cartProducts ->
                    when(cartProducts) {
                        is Resource.Loading -> {
                            ProgressProduct()
                        }
                        is Resource.Success -> {
                            CartContent(
                                products = cartProducts.data,
                                navigateToDetail = navigateToDetail,
                                onClickResponse = { productData ->
                                    cartViewModel.setCartProduct(productData.productId, false)
                                    cartViewModel.setAllCarts(productData.productId)
                                }
                            )
                        }
                        is Resource.Error -> {
                            Text(text = stringResource(R.string.error_product), color = MaterialTheme.colors.onSurface)
                        }
                    }
                }
//                viewModel.uiStateDbProducts.collectAsState(initial = UiState.Loading).value.let { uiState ->
//                    when (uiState) {
//                        is UiState.Loading -> {
//                            viewModel.getProductsDb()
//                            ProgressProduct()
//                        }
//
//                        is UiState.Success -> {
//                            CartContent(products = uiState.data, navigateToDetail = navigateToDetail, viewModel = viewModel)
//                        }
//
//                        is UiState.Error -> {
//                            Text(text = stringResource(R.string.error_product), color = MaterialTheme.colors.onSurface)
//                        }
//                    }
//                }
            }
        }
    )
}