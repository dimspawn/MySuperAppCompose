package com.imaginatic.mysuperappcompose.ui.screen.search.section

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.imaginatic.mysuperappcompose.ui.component.EmptyProduct
import com.imaginatic.mysuperappcompose.ui.component.ProductItem
import com.mysuperappcompose.core.domain.model.ProductData

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchContent(
    modifier: Modifier,
    listProduct: List<ProductData>?,
    navigateToDetail: (Int) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        if (listProduct != null) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(140.dp),
                content = {
                    items(listProduct, key = { it.productId }) { product ->
                        ProductItem(
                            product = product,
                            modifier = modifier
                                .fillMaxWidth()
                                .animateItemPlacement(tween(delayMillis = 100))
                                .clickable {
                                    navigateToDetail(product.productId)
                                }
                        )
                    }
                }, contentPadding = PaddingValues(8.dp)
            )
//            LazyVerticalGrid(
//                columns = GridCells.Adaptive(140.dp),
//                content = {
//                    items(listProduct, key = { it.id ?: -1 }) { product ->
//                        ProductItem(
//                            product = product,
//                            modifier = modifier
//                                .fillMaxWidth()
//                                .animateItemPlacement(tween(durationMillis = 100))
//                                .clickable {
//                                    navigateToDetail(product.id ?: return@clickable)
//                                }
//                        )
//                    }
//                }, contentPadding = PaddingValues(8.dp)
//            )
            if (listProduct.isEmpty()) EmptyProduct()
        } else EmptyProduct()
    }
}