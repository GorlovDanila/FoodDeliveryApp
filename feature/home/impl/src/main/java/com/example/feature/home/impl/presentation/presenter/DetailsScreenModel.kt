package com.example.feature.home.impl.presentation.presenter

import androidx.compose.runtime.Immutable
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.example.feature.home.api.model.FoodInfo
import com.example.feature.home.api.usecase.GetFoodByIdUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

@Immutable
data class DetailsScreenState(
    val isLoading: Boolean = false,
    val animInfo: FoodInfo? = null,
)

sealed interface DetailsEvent {
    data class OnLoadFoodById(val id: Long) : DetailsEvent
}

sealed interface DetailsAction {
    data class ShowToast(val text: String) : DetailsAction
}

class DetailsScreenModel(
    private val getFoodByIdUseCase: GetFoodByIdUseCase,
) : ScreenModel {

    private val _state = MutableStateFlow(DetailsScreenState())
    val state: StateFlow<DetailsScreenState>
        get() = _state.asStateFlow()

    private val _action = MutableSharedFlow<DetailsAction?>()
    val action: SharedFlow<DetailsAction?>
        get() = _action.asSharedFlow()

    fun event(detailEvent: DetailsEvent) {
        when (detailEvent) {
            is DetailsEvent.OnLoadFoodById -> onLoadFoodById(detailEvent.id)
        }
    }

    private fun onLoadFoodById(id: Long) {
        screenModelScope.launch {
            try {
                _state.emit(
                    _state.value.copy(
                        isLoading = true
                    )
                )
                _state.emit(
                    _state.value.copy(
                        animInfo = getFoodByIdUseCase.invoke(id)
                    )
                )
            } catch (e: HttpException) {
                _action.emit(DetailsAction.ShowToast("An unexpected error has occurred: ${e}!"))
            } finally {
                _state.emit(
                    _state.value.copy(
                        isLoading = false
                    )
                )
            }
        }
    }
}