package com.imaginatic.mysuperappcompose.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.imaginatic.mysuperappcompose.ui.component.ProgressProduct
import com.imaginatic.mysuperappcompose.ui.screen.home.section.HomeContent
import com.mysuperappcompose.core.R
import com.mysuperappcompose.core.data.Resource
import com.mysuperappcompose.core.ui.SearchBar
import com.mysuperappcompose.core.ui.MainTemplate
import com.mysuperappcompose.core.ui.theme.Gray200

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    //viewModel: HomeViewModel = hiltViewModel(),
    homeViewModel: HomeViewModel,
    navigateToDetail: (Int) -> Unit,
    navigateToSearch: () -> Unit,
) {
    MainTemplate(
        modifier = modifier,
        topBar = {
            SearchBar(
                //query = "",
                //onQueryChange = {},
                context = LocalContext.current,
                modifier = Modifier.background(MaterialTheme.colors.primary),
                isEnabled = false,
                onSearchClicked = { navigateToSearch() }
            )
        },
        content = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = modifier
                    .fillMaxSize()
                    .background(Gray200)
            ) {
                homeViewModel.products.collectAsState(initial = Resource.Loading()).value.let { products ->
                    when (products) {
                        is Resource.Loading -> {
                            ProgressProduct()
                        }
                        is Resource.Success -> {
                            HomeContent(
                                modifier = modifier,
                                listProduct = products.data,
                                navigateToDetail = navigateToDetail
                            )
                        }
                        is Resource.Error -> {
                            Text(text = stringResource(id = R.string.error_product), color = MaterialTheme.colors.onSurface)
                        }
                    }
                }
//                viewModel.uiStateProduct.collectAsState(initial = UiState.Loading).value.let { uiState ->
//                    when (uiState) {
//                        is UiState.Loading -> {
//                            viewModel.getProductsApiCall()
//                            ProgressProduct()
//                        }
//
//                        is UiState.Success -> {
//                            HomeContent(
//                                modifier = modifier,
//                                listProduct = uiState.data.products,
//                                navigateToDetail = navigateToDetail,
//                            )
//                        }
//
//                        is UiState.Error -> {
//                            Text(text = stringResource(R.string.error_product), color = MaterialTheme.colors.onSurface)
//                        }
//                    }
//                }
            }
        })
}