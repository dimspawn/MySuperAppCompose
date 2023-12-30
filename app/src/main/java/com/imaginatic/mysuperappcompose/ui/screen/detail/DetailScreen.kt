package com.imaginatic.mysuperappcompose.ui.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.imaginatic.mysuperappcompose.ui.component.ProgressProduct
import com.imaginatic.mysuperappcompose.ui.screen.detail.section.DetailContent
import com.mysuperappcompose.core.R
import com.mysuperappcompose.core.data.Resource
import com.mysuperappcompose.core.ui.theme.Gray200

@Composable
fun DetailScreen(
    productId: Int,
    //viewModel: DetailViewModel = hiltViewModel(),
    detailViewModel: DetailViewModel,
    navigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.detail_product))
                },
                navigationIcon = {
                    IconButton(onClick = { navigateBack() }) {
                        Icon(Icons.Filled.ArrowBack, "Back Icon")
                    }
                },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White,
                elevation = 0.dp
            )
        }, content = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Gray200)
                    .padding(it)
            ) {
                detailViewModel.setDetailId(productId)
                detailViewModel.detailProduct.collectAsState(initial = Resource.Loading()).value.let { detailProduct ->
                    when(detailProduct) {
                        is Resource.Loading -> {
                            ProgressProduct()
                        }
                        is Resource.Success -> {
                            DetailContent(product = detailProduct.data?.get(0), detailViewModel = detailViewModel)
                        }
                        is Resource.Error -> {
                            Text(text = stringResource(id = R.string.error_product), color = MaterialTheme.colors.onSurface)
                        }
                    }
                }
//                viewModel.uiStateProduct.collectAsState(initial = UiState.Loading).value.let { uiState ->
//                    when (uiState) {
//                        is UiState.Loading -> {
//                            viewModel.getProductByIdApiCall(productId)
//                            ProgressProduct()
//                        }
//
//                        is UiState.Success -> {
//                            DetailContent(product = uiState.data, viewModel = viewModel)
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