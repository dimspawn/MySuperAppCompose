package com.imaginatic.mysuperappcompose.ui.screen.detail

import androidx.lifecycle.ViewModel
import com.mysuperappcompose.core.domain.usecase.ShopUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class DetailViewModel @Inject constructor(
    private val shopUseCase: ShopUseCase
) : ViewModel() {

    private val id = MutableStateFlow(-1)
    private val imageId = MutableStateFlow(-1)

    val detailProduct = id.flatMapLatest {
        shopUseCase.getProductById(it)
    }

    val imagesProduct = imageId.flatMapLatest {
        shopUseCase.getImagesProduct(it)
    }

    fun setDetailId(id: Int) {
        this.id.value = id
    }

    fun setImageId(imageId: Int) {
        this.imageId.value = imageId
    }

    fun setCartProduct(id: Int, cart: Boolean) {
        shopUseCase.setCartProduct(id, cart)
    }
}