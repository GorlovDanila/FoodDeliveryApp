package com.example.feature.auth.impl.presentation.presenter

import androidx.compose.runtime.Immutable
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.example.feature.auth.api.usecase.RegisterUserUseCase
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
)

sealed interface RegistrationEvent {
    data class OnRegisterUser(val login: String, val password: String) : RegistrationEvent
    data object OnNavigateClick : RegistrationEvent
}

sealed interface RegistrationAction {
    data object Navigate : RegistrationAction
    data class ShowToast(val text: String) : RegistrationAction
}

class RegistrationScreenModel(
    private val registerUserUseCase: RegisterUserUseCase,
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

            is RegistrationEvent.OnNavigateClick -> onNavigateClick()
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
                    onNavigateClick()
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

    private fun onNavigateClick() {
        screenModelScope.launch { _action.emit(RegistrationAction.Navigate) }
    }
}
