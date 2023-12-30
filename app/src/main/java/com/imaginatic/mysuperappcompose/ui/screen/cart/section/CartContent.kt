package com.imaginatic.mysuperappcompose.ui.screen.cart.section

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.imaginatic.mysuperappcompose.ui.component.EmptyProduct
import com.imaginatic.mysuperappcompose.ui.component.ProductCartItem
import com.mysuperappcompose.core.domain.model.ProductData

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CartContent(
    products: List<ProductData>?,
    navigateToDetail: (Int) -> Unit,
    onClickResponse: (ProductData) -> Unit
) {
    val listProduct = products ?: emptyList()
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        content = {
            items(listProduct, key = { it.productId }) { product ->
                ProductCartItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateItemPlacement(tween(durationMillis = 100))
                        .clickable {
                            navigateToDetail(product.productId)
                        },
                    product = product,
                    onRemoveClicked = {
                        onClickResponse(it)
                    }
                )
            }
        }, contentPadding = PaddingValues(8.dp)
    )
    if (listProduct.isEmpty()) EmptyProduct()
}