package com.imaginatic.mysuperappcompose.ui.screen.home

import androidx.lifecycle.ViewModel
import com.mysuperappcompose.core.domain.usecase.ShopUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val shopUseCase: ShopUseCase
) : ViewModel() {

    private val query = MutableStateFlow("")
    val products = shopUseCase.getProducts()
    //val searchProducts = shopUseCase.searchProduct(query.toString())
    @OptIn(ExperimentalCoroutinesApi::class)
    val searchProducts = query.flatMapLatest {
        shopUseCase.searchProduct(it)
    }
//    private val _uiStateProduct: MutableStateFlow<UiState<ProductResponse>> = MutableStateFlow(
//        UiState.Loading)
//    val uiStateProduct: StateFlow<UiState<ProductResponse>> = _uiStateProduct

    fun setQuery(query: String) {
        this.query.value = query
    }
    //private val _query = mutableStateOf("")
    //val query: State<String> get() = _query

    //private val queries = MutableLiveData<String>()
    //@OptIn(ExperimentalCoroutinesApi::class)
    //val searchProducts = _query.

//    fun getProductsApiCall() { // this is sample not using `suspend`
//        Log.d("cartTag", "Marco 1")
//        getProductsUseCase.execute(Unit).onEach { product ->
//            Log.d("cartTag", "Marco 2")
//            _uiStateProduct.value = UiState.Success(product)
//        }.catch { e ->
//            Log.d("cartTag", "Marco 3")
//            _uiStateProduct.value = UiState.Error(e.message.toString())
//        }.launchIn(viewModelScope)
//    }

//    fun searchProductApiCall(query: String) {
//        _query.value = query
//        viewModelScope.launch {
//            try {
//                searchProductUseCase.execute(_query.value)
//                    .catch {
//                        _uiStateProduct.value = UiState.Error(it.message.toString())
//                    }
//                    .collect { product ->
//                        _uiStateProduct.value = UiState.Success(product)
//                    }
//            } catch (e: Exception) {
//                _uiStateProduct.value = UiState.Error(e.message.toString())
//            }
//        }
//    }
}