package com.example.feature.cart.impl.presentation.presenter

import androidx.compose.runtime.Immutable
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.example.feature.cart.api.model.ProductInfo
import com.example.feature.cart.api.usecase.DeleteAllProductsUseCase
import com.example.feature.cart.api.usecase.DeleteProductUseCase
import com.example.feature.cart.api.usecase.GetAllProductsUseCase
import com.example.feature.cart.api.usecase.GetSummaryPriceUseCase
import com.example.feature.cart.api.usecase.UpdateProductUseCase
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@Immutable
data class ShoppingCartScreenState(
    val isLoading : Boolean = false,
    val products : PersistentList<ProductInfo>? = null,
    val sumPrice : String = "0",
)

sealed interface ShoppingCartEvent {
    data object OnLoadProducts : ShoppingCartEvent
    data class OnPlusProductCount(val productInfo: ProductInfo) : ShoppingCartEvent
    data class OnMinusProductCount(val productInfo: ProductInfo) : ShoppingCartEvent
    data object OnSumPrice : ShoppingCartEvent
    data object OnCreateOrder : ShoppingCartEvent
}

sealed interface ShoppingCartAction {
    data class ShowToast(val text: String) : ShoppingCartAction
}

private const val ERROR: String = "Произошла неизвестная ошибка!"
private const val SUCCESS: String = "Заказ успешно создан"

class ShoppingCartScreenModel(
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val deleteProductUseCase: DeleteProductUseCase,
    private val deleteAllProductsUseCase: DeleteAllProductsUseCase,
    private val updateProductUseCase: UpdateProductUseCase,
    private val getSummaryPriceUseCase: GetSummaryPriceUseCase,
) : ScreenModel {

    private val _state = MutableStateFlow(ShoppingCartScreenState())
    val state: StateFlow<ShoppingCartScreenState>
        get() = _state.asStateFlow()

    private val _action = MutableSharedFlow<ShoppingCartAction?>()
    val action: SharedFlow<ShoppingCartAction?>
        get() = _action.asSharedFlow()

    fun event(shoppingCartEvent: ShoppingCartEvent) {
        when (shoppingCartEvent) {
            is ShoppingCartEvent.OnLoadProducts -> onLoadProducts()
            is ShoppingCartEvent.OnPlusProductCount -> onPlusProduct(shoppingCartEvent.productInfo)
            is ShoppingCartEvent.OnMinusProductCount -> onMinusProduct(shoppingCartEvent.productInfo)
            is ShoppingCartEvent.OnSumPrice -> onSumPrice()
            is ShoppingCartEvent.OnCreateOrder -> onCreateOrder()
        }
    }

    private fun onLoadProducts() {
        screenModelScope.launch {
            try {
                _state.emit(
                    _state.value.copy(
                        products = getAllProductsUseCase.invoke().toPersistentList()
                    )
                )
                onSumPrice()
            } catch (e: Exception) {
                _action.emit(ShoppingCartAction.ShowToast(ERROR))
            }
        }
    }

    private fun onPlusProduct(productInfo: ProductInfo) {
        screenModelScope.launch {
            try {
                updateProductUseCase(productInfo.copy(count = productInfo.count + 1))
                onLoadProducts()
            } catch (e: Exception) {
                _action.emit(ShoppingCartAction.ShowToast(ERROR))
            }
        }
    }

    private fun onMinusProduct(productInfo: ProductInfo) {
        screenModelScope.launch {
            try {
                if (productInfo.count - 1 == 0) {
                    deleteProductUseCase(productInfo)
                } else {
                    updateProductUseCase(productInfo.copy(count = productInfo.count - 1))
                }
                onLoadProducts()
            } catch (e: Exception) {
                _action.emit(ShoppingCartAction.ShowToast(ERROR))
            }
        }
    }

    private fun onSumPrice() = screenModelScope.launch {
        _state.emit(
            _state.value.copy(
                sumPrice = getSummaryPriceUseCase.invoke()
            )
        )
    }

    private fun onCreateOrder() = screenModelScope.launch {
        _action.emit(ShoppingCartAction.ShowToast(SUCCESS))
        deleteAllProductsUseCase.invoke()
        onLoadProducts()
    }
}
