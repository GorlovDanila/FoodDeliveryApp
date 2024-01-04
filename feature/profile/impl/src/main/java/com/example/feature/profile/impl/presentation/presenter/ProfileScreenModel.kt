package com.example.feature.profile.impl.presentation.presenter

import androidx.compose.runtime.Immutable
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.example.feature.auth.api.model.User
import com.example.feature.auth.api.usecase.GetCurrentUserUseCase
import com.example.feature.auth.api.usecase.OnSignOutUserUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@Immutable
data class ProfileScreenState(
    val userInfo: User? = null,
)

sealed interface ProfileEvent {
    data object OnSignOutClick : ProfileEvent
    data object OnLoadUser : ProfileEvent
}

sealed interface ProfileAction {
    data object Navigate : ProfileAction
    data class ShowToast(val text: String) : ProfileAction
}

class ProfileScreenModel(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val onSignOutUserUseCase: OnSignOutUserUseCase,
) : ScreenModel {

    private val _state = MutableStateFlow(ProfileScreenState())
    val state: StateFlow<ProfileScreenState>
        get() = _state.asStateFlow()

    private val _action = MutableSharedFlow<ProfileAction?>()
    val action: SharedFlow<ProfileAction?>
        get() = _action.asSharedFlow()

    fun event(homeEvent: ProfileEvent) {
        when (homeEvent) {
            is ProfileEvent.OnSignOutClick -> onSignOutClick()
            is ProfileEvent.OnLoadUser -> onLoadUser()
        }
    }

    private fun onLoadUser() {
        screenModelScope.launch {
            try {
                _state.emit(
                    _state.value.copy(
                        userInfo = getCurrentUserUseCase.invoke()
                    )
                )
            } catch (e: Exception) {
                _action.emit(ProfileAction.ShowToast("An unexpected error has occurred: ${e}!"))
            }
        }
    }

    private fun onSignOutClick() {
        screenModelScope.launch {
            onSignOutUserUseCase.invoke()
            _action.emit(ProfileAction.Navigate)
        }
    }
}
