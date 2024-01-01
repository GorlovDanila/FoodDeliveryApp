package com.example.feature.home.impl.presentation.presenter

import androidx.compose.runtime.Immutable
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.example.feature.home.api.model.FoodInfo
import com.example.feature.home.api.model.FoodListInfo
import com.example.feature.home.api.usecase.GetFoodListUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

@Immutable
data class HomeScreenState(
    val isLoading: Boolean = false,
    val foods: FoodListInfo? = null,
    val animInfo: FoodInfo? = null,
)

sealed interface HomeEvent {
    data object OnLoadFood : HomeEvent
    data class OnFoodClick(val foodInfo: FoodInfo) : HomeEvent
}

sealed interface HomeAction {
    data class Navigate(val foodId: Long) : HomeAction
    data class ShowToast(val text: String) : HomeAction
}

class HomeScreenModel(
    private val getFoodListUseCase: GetFoodListUseCase,
) : ScreenModel {

    private val _state = MutableStateFlow(HomeScreenState())
    val state: StateFlow<HomeScreenState>
        get() = _state.asStateFlow()

    private val _action = MutableSharedFlow<HomeAction?>()
    val action: SharedFlow<HomeAction?>
        get() = _action.asSharedFlow()

    fun event(homeEvent: HomeEvent) {
        when (homeEvent) {
            is HomeEvent.OnLoadFood -> onLoadFood()
            is HomeEvent.OnFoodClick -> onFoodClick(homeEvent.foodInfo)
        }
    }

    private fun onLoadFood() {
        screenModelScope.launch {
            try {
                _state.emit(
                    _state.value.copy(
                        isLoading = true
                    )
                )
                _state.emit(
                    _state.value.copy(
                        foods = getFoodListUseCase.invoke()
                    )
                )
            } catch (e: HttpException) {
                _action.emit(HomeAction.ShowToast("An unexpected error has occurred: ${e}!"))
            } finally {
                _state.emit(
                    _state.value.copy(
                        isLoading = false
                    )
                )
            }
        }
    }

    private fun onFoodClick(foodInfo: FoodInfo) {
        screenModelScope.launch { _action.emit(HomeAction.Navigate(foodId = foodInfo.id)) }
    }
}
