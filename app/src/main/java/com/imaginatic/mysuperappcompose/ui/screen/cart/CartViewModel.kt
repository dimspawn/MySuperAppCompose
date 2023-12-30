package com.imaginatic.mysuperappcompose.ui.screen.cart

import androidx.lifecycle.ViewModel
import com.mysuperappcompose.core.domain.usecase.ShopUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class CartViewModel @Inject constructor(
    private val shopUseCase: ShopUseCase
): ViewModel() {

    private val productId = MutableStateFlow(-1)

    val cartProducts = productId.flatMapLatest {
        shopUseCase.getCartProducts()
    }

    fun setCartProduct(id: Int, cart: Boolean) {
        shopUseCase.setCartProduct(id, cart)
    }

    fun setAllCarts(productId: Int) {
        this.productId.value = productId
    }
}