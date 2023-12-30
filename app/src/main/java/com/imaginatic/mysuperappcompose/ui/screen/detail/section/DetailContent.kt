package com.imaginatic.mysuperappcompose.ui.screen.detail.section

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.imaginatic.mysuperappcompose.ui.screen.detail.DetailViewModel
import com.mysuperappcompose.core.R
import com.mysuperappcompose.core.data.Resource
import com.mysuperappcompose.core.domain.model.ProductData
import com.mysuperappcompose.core.ui.theme.Gray200
import com.mysuperappcompose.core.utils.Dimens
import com.mysuperappcompose.core.utils.Extensions.myToast

@Composable
fun DetailContent(
    product: ProductData?,
    detailViewModel: DetailViewModel
) {
    val context = LocalContext.current
    val strBuy = stringResource(R.string.buy)
    val strAddedCart = stringResource(R.string.added_to_cart)
    val strThanks = stringResource(R.string.thank_you_buy)
    val productId = product?.productId ?: -1
    val isCharted = product?.cart ?: false
    var buyText by remember { mutableStateOf(strBuy) }
    var isAlreadyOnCart by remember { mutableStateOf(isCharted) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight()
            .background(color = Color.White),
    ) {
        if (productId != -1) {
            detailViewModel.setImageId(productId)
            detailViewModel.imagesProduct.collectAsState(initial = Resource.Loading()).value.let {  imagesProduct ->
                when(imagesProduct) {
                    is Resource.Loading -> {
                        Text(
                            text = "Loading Images...",
                            modifier = Modifier
                                .padding(vertical = 100.dp)
                                .align(Alignment.CenterHorizontally),
                            color = MaterialTheme.colors.primary
                        )
                    }
                    is Resource.Success -> {
                        ImageProductPager(product = imagesProduct.data)
                    }
                    is Resource.Error -> {
                        Text(
                            text = "Error Get the Images",
                            modifier = Modifier
                                .padding(vertical = 100.dp)
                                .align(Alignment.CenterHorizontally),
                            color = MaterialTheme.colors.primary
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.size(Dimens.dp8))
        TitleProduct(productTitle = product?.title ?: "[Title Here]", productPrice = product?.price ?: 0)
        Divider(color = Gray200, thickness = 10.dp)
        DescriptionProduct(productDesc = product?.description ?: "[description]")
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        Alignment.BottomStart
    ) {
        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
            if (!isAlreadyOnCart) {
                Text(
                    text = stringResource(id = R.string.add_to_cart),
                    modifier = Modifier
                        .background(MaterialTheme.colors.secondary)
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(vertical = 20.dp)
                        .clickable {
                            context.myToast(strAddedCart)
                            detailViewModel.setCartProduct(productId, true)
                            isAlreadyOnCart = false
                        },
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            }
            Text(
                text = buyText,
                modifier = Modifier
                    .background(MaterialTheme.colors.primary)
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(vertical = 20.dp)
                    .clickable {
                        context.myToast(strThanks)
                        buyText = strThanks
                    },
                color = Color.White,
                textAlign = TextAlign.Center,
            )
        }
    }
//    Box(
//        modifier = Modifier.fillMaxSize(),
//        Alignment.BottomStart
//    ) {
//        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
//            viewModel.uiStateDbProduct.collectAsState(initial = UiState.Loading).value.let { uiState ->
//                when (uiState) {
//                    is UiState.Loading -> {
//                        //viewModel.getProductByIdDb(productId)
//                    }
//
//                    is UiState.Success -> {
//                        isAlreadyOnCart = true
//                    }
//
//                    is UiState.Error -> {}
//                }
//            }
//            if (!isAlreadyOnCart) {
//                Text(
//                    text = stringResource(R.string.add_to_cart),
//                    modifier = Modifier
//                        .background(MaterialTheme.colors.secondary)
//                        .fillMaxWidth()
//                        .weight(1f)
//                        .padding(vertical = 20.dp)
//                        .clickable {
//                            context.myToast(strAddedCart)
//                            //viewModel.insertProductDb(product)
//                        },
//                    textAlign = TextAlign.Center,
//                    color = Color.White,
//                )
//            }
//            Text(
//                text = buyText,
//                modifier = Modifier
//                    .background(MaterialTheme.colors.primary)
//                    .fillMaxWidth()
//                    .weight(1f)
//                    .padding(vertical = 20.dp)
//                    .clickable {
//                        context.myToast(strThanks)
//                        buyText = strThanks
//                    },
//                color = Color.White,
//                textAlign = TextAlign.Center,
//            )
//        }
//    }
}