package com.example.feature.auth.impl.presentation.presenter

import androidx.compose.runtime.Immutable
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import cafe.adriel.voyager.core.screen.Screen
import com.example.feature.auth.api.usecase.IsAuthenticatedUserUseCase
import com.example.feature.auth.api.usecase.IsFirstLaunchUserUseCase
import com.example.feature.auth.api.usecase.RegisterUserUseCase
import com.example.feature.auth.impl.presentation.ui.AuthorizationScreen
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
data class RegistrationScreenState(
    val isLoading: Boolean = false,
    val isAuthenticated: Boolean? = null,
    val isFirstLaunch: Boolean? = null,
)

sealed interface RegistrationEvent {
    data class OnRegisterUser(val login: String, val password: String) : RegistrationEvent
    data class OnNavigate(val screen: Screen) : RegistrationEvent
    data object IsAuthenticatedCheck : RegistrationEvent
    data object IsFirstLaunchCheck : RegistrationEvent
}

sealed interface RegistrationAction {
    data class Navigate(val screen: Screen) : RegistrationAction
    data class ShowToast(val text: String) : RegistrationAction
}

class RegistrationScreenModel(
    private val registerUserUseCase: RegisterUserUseCase,
    private val isAuthenticatedUserUseCase: IsAuthenticatedUserUseCase,
    private val isFirstLaunchUserUseCase: IsFirstLaunchUserUseCase,
) : ScreenModel {

    private val _state = MutableStateFlow(RegistrationScreenState())
    val state: StateFlow<RegistrationScreenState>
        get() = _state.asStateFlow()

    private val _action = MutableSharedFlow<RegistrationAction?>()
    val action: SharedFlow<RegistrationAction?>
        get() = _action.asSharedFlow()

    fun event(registrationEvent: RegistrationEvent) {
        when (registrationEvent) {
            is RegistrationEvent.OnRegisterUser -> onRegisterUser(
                registrationEvent.login,
                registrationEvent.password
            )
            is RegistrationEvent.OnNavigate -> onNavigate(registrationEvent.screen)
            is RegistrationEvent.IsAuthenticatedCheck -> isAuthenticatedCheck()
            is RegistrationEvent.IsFirstLaunchCheck -> isFirstLaunchCheck()
        }
    }

    private fun onRegisterUser(login: String, password: String) {
        screenModelScope.launch {
            try {
                _state.emit(
                    _state.value.copy(
                        isLoading = true
                    )
                )

                val statusCode = registerUserUseCase.invoke(login, password)
                if (statusCode.code == AuthConstants.BAD_REQUEST.name) {
                    _action.emit(RegistrationAction.ShowToast("Пользователь с такими данными уже существует"))
                } else {
                    _action.emit(RegistrationAction.ShowToast("Пользователь успешно зарегистрирован"))
                    onNavigate(AuthorizationScreen())
                }

            } catch (e: HttpException) {
                _action.emit(RegistrationAction.ShowToast("An unexpected error has occurred: ${e}!"))
            } finally {
                _state.emit(
                    _state.value.copy(
                        isLoading = false
                    )
                )
            }
        }
    }

    private fun onNavigate(screen: Screen) {
        screenModelScope.launch { _action.emit(RegistrationAction.Navigate(screen)) }
    }

    private fun isAuthenticatedCheck() {
        screenModelScope.launch {
            _state.emit(
                _state.value.copy(
                    isAuthenticated = isAuthenticatedUserUseCase.invoke()
                )
            )
        }
    }

    private fun isFirstLaunchCheck() {
        screenModelScope.launch {
            _state.emit(
                _state.value.copy(
                    isFirstLaunch = isFirstLaunchUserUseCase.invoke()
                )
            )
        }
    }
}
