package com.example.feature.auth.impl.presentation.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.core.navigation.SharedScreen
import com.example.feature.auth.impl.presentation.presenter.AuthorizationAction
import com.example.feature.auth.impl.presentation.presenter.AuthorizationEvent
import com.example.feature.auth.impl.presentation.presenter.AuthorizationScreenModel
import com.example.feature.auth.impl.presentation.presenter.AuthorizationScreenState

class AuthorizationScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel = getScreenModel<AuthorizationScreenModel>()
        val state = screenModel.state.collectAsStateWithLifecycle()
        val action by screenModel.action.collectAsStateWithLifecycle(null)

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {

            AuthorizationContent(
                screenState = state.value,
                eventHandler = screenModel::event
            )

            AuthorizationScreenActions(
                navigator = navigator,
                screenAction = action
            )
        }
    }
}

@Composable
fun AuthorizationContent(
    screenState: AuthorizationScreenState,
    eventHandler: (AuthorizationEvent) -> Unit,
) {
    if (screenState.isLoading) {
        CircularProgressIndicator(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .wrapContentHeight(Alignment.CenterVertically)
        )
    } else {
        AuthorizationUI(eventHandler)
    }
}

@Composable
private fun AuthorizationScreenActions(
    navigator: Navigator,
    screenAction: AuthorizationAction?
) {
    val context = LocalContext.current
    val homeScreen = rememberScreen(SharedScreen.HomeScreen)

    LaunchedEffect(screenAction) {
        when (screenAction) {
            null -> Unit
            is AuthorizationAction.Navigate -> {
                navigator.push(homeScreen)
            }
            is AuthorizationAction.ShowToast -> {
                Toast.makeText(context, screenAction.text, Toast.LENGTH_LONG).show()
            }
        }
    }
}

@Composable
fun AuthorizationUI(
    eventHandler: (AuthorizationEvent) -> Unit,
) {

    var login by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = login,
            onValueChange = { login = it },
            label = { Text("Enter login") },
        )

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Enter password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Button(onClick = {
            eventHandler.invoke(
                AuthorizationEvent.OnAuthorizeUser(
                    login,
                    password
                )
            )
        }) {
            Text(text = "Войти")
        }
    }
}
