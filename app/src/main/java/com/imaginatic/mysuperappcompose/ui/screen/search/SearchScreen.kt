package com.imaginatic.mysuperappcompose.ui.screen.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.imaginatic.mysuperappcompose.ui.component.ProgressProduct
import com.imaginatic.mysuperappcompose.ui.screen.home.HomeViewModel
import com.imaginatic.mysuperappcompose.ui.screen.search.section.SearchContent
import com.mysuperappcompose.core.R
import com.mysuperappcompose.core.data.Resource
import com.mysuperappcompose.core.ui.MainTemplate
import com.mysuperappcompose.core.ui.theme.Gray200
import com.mysuperappcompose.core.ui.SearchBar

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel,
    //viewModel: HomeViewModel = hiltViewModel(),
    navigateToDetail: (Int) -> Unit,
    navigateBack: () -> Unit,
) {
    //val query by homeViewModel.query
    val focusRequester = remember { FocusRequester() }
    val contextDel = LocalContext.current
    //val uiStateProduct by remember { viewModel.uiStateProduct }.collectAsState()

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    MainTemplate(
        modifier = modifier,
        topBar = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(
                    onClick = navigateBack,
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(id = R.string.back)
                    )
                }
                SearchBar(
                    //query = query,
                    context = LocalContext.current,
                    modifier = Modifier
                        .background(MaterialTheme.colors.primary)
                        .focusRequester(focusRequester),
                    onQueryChange = {
                        //Toast.makeText(contextDel, it, Toast.LENGTH_SHORT).show()
                        homeViewModel.setQuery(query = it)
                    }
                )
//                SearchBar(
//                    query = query,
//                    onQueryChange = viewModel::searchProductApiCall,
//                    modifier = Modifier
//                        .background(MaterialTheme.colors.primary)
//                        .focusRequester(focusRequester),
//                )
            }
        },
        content = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = modifier
                    .fillMaxSize()
                    .background(Gray200)
            ) {
                homeViewModel.searchProducts.collectAsState(initial = Resource.Loading()).value.let {  searchProduct ->
                    when(searchProduct) {
                        is Resource.Loading -> { ProgressProduct() }
                        is Resource.Success -> {
                            SearchContent(
                                modifier = modifier,
                                listProduct = searchProduct.data,
                                navigateToDetail = navigateToDetail
                            )
                        }
                        is Resource.Error -> { Text(text = stringResource(id = R.string.error_product), color = MaterialTheme.colors.onSurface) }
                    }

                }
//                when (uiStateProduct) {
//                    is UiState.Loading -> {
//                        viewModel.getProductsApiCall()
//                        ProgressProduct()
//                    }
//
//                    is UiState.Success -> {
//                        SearchContent(
//                            modifier = modifier,
//                            listProduct = (uiStateProduct as UiState.Success<ProductResponse>).data.products,
//                            navigateToDetail = navigateToDetail,
//                        )
//                    }
//
//                    is UiState.Error -> {
//                        Text(text = stringResource(R.string.error_product), color = MaterialTheme.colors.onSurface)
//                    }
//                }
            }
        }
    )
}