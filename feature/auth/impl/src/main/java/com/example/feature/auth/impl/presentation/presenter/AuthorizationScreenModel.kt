package com.example.feature.auth.impl.presentation.presenter

import androidx.compose.runtime.Immutable
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.example.feature.auth.api.usecase.AuthorizeUserUseCase
import com.example.feature.auth.api.usecase.GetCurrentUserUseCase
import com.example.feature.auth.api.usecase.OnAuthenticationUserUseCase
import com.example.feature.auth.api.usecase.SaveCurrentUserUseCase
import com.example.feature.auth.impl.utils.AuthConstants
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

@Immutable
data class AuthorizationScreenState(
    val isLoading: Boolean = false,
)

sealed interface AuthorizationEvent {
    data class OnAuthorizeUser(val login: String, val password: String) : AuthorizationEvent
}

sealed interface AuthorizationAction {
    data object Navigate : AuthorizationAction
    data class ShowToast(val text: String) : AuthorizationAction
}

class AuthorizationScreenModel(
    private val authorizeUserUseCase: AuthorizeUserUseCase,
    private val saveCurrentUserUseCase: SaveCurrentUserUseCase,
    private val onAuthenticationUserUseCase: OnAuthenticationUserUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
) : ScreenModel {

    private val _state = MutableStateFlow(AuthorizationScreenState())
    val state: StateFlow<AuthorizationScreenState>
        get() = _state.asStateFlow()

    private val _action = MutableSharedFlow<AuthorizationAction?>()
    val action: SharedFlow<AuthorizationAction?>
        get() = _action.asSharedFlow()

    fun event(authorizationEvent: AuthorizationEvent) {
        when (authorizationEvent) {
            is AuthorizationEvent.OnAuthorizeUser -> onAuthorizeUser(
                authorizationEvent.login,
                authorizationEvent.password
            )
        }
    }

    private fun onAuthorizeUser(login: String, password: String) {
        screenModelScope.launch {
            try {
                _state.emit(
                    _state.value.copy(
                        isLoading = true
                    )
                )

                val statusCode = authorizeUserUseCase.invoke(login, password)
                if (statusCode.code == AuthConstants.BAD_REQUEST.name) {
                    _action.emit(AuthorizationAction.ShowToast("Пользователя с такими данными не существует"))
                } else {
                    saveCurrentUserUseCase.invoke(login, password)
                    onAuthenticationUserUseCase.invoke()
                    onNavigateClick()
                }

            } catch (e: HttpException) {
                _action.emit(AuthorizationAction.ShowToast("An unexpected error has occurred: ${e}!"))
            } finally {
                _state.emit(
                    _state.value.copy(
                        isLoading = false
                    )
                )
            }
        }
    }

    private fun onNavigateClick() {
        screenModelScope.launch { _action.emit(AuthorizationAction.Navigate) }
    }
}
