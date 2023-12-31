package com.example.feature.auth.impl.presentation.ui

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.core.navigation.SharedScreen
import com.example.feature.auth.impl.R
import com.example.feature.auth.impl.presentation.presenter.RegistrationAction
import com.example.feature.auth.impl.presentation.presenter.RegistrationEvent
import com.example.feature.auth.impl.presentation.presenter.RegistrationScreenModel
import com.example.feature.auth.impl.presentation.presenter.RegistrationScreenState

class RegistrationScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel = getScreenModel<RegistrationScreenModel>()
        val state = screenModel.state.collectAsStateWithLifecycle()
        val action by screenModel.action.collectAsStateWithLifecycle(null)

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {

            RegistrationContent(
                screenState = state.value,
                eventHandler = screenModel::event
            )

            RegistrationScreenActions(
                navigator = navigator,
                screenAction = action
            )
        }
    }
}

@Composable
fun RegistrationContent(
    screenState: RegistrationScreenState,
    eventHandler: (RegistrationEvent) -> Unit,
) {
    eventHandler.invoke(RegistrationEvent.IsAuthenticatedCheck)
    eventHandler.invoke(RegistrationEvent.IsFirstLaunchCheck)
    if (screenState.isLoading) {
        CircularProgressIndicator(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .wrapContentHeight(Alignment.CenterVertically)
        )
    } else {
        RegistrationUI(eventHandler, screenState)
    }
}

@Composable
private fun RegistrationScreenActions(
    navigator: Navigator,
    screenAction: RegistrationAction?
) {
    val context = LocalContext.current
    LaunchedEffect(screenAction) {
        when (screenAction) {
            null -> Unit
            is RegistrationAction.Navigate -> {
                navigator.push(screenAction.screen)
            }

            is RegistrationAction.ShowToast -> {
                Toast.makeText(context, screenAction.text, Toast.LENGTH_LONG).show()
            }
        }
    }
}

@Composable
fun RegistrationUI(
    eventHandler: (RegistrationEvent) -> Unit,
    screenState: RegistrationScreenState,
) {
    if (screenState.isAuthenticated == true) {
        val homeScreen = rememberScreen(SharedScreen.HomeScreen)
        eventHandler.invoke(RegistrationEvent.OnNavigate(homeScreen))
    } else {
        var login by rememberSaveable { mutableStateOf("") }
        var password by rememberSaveable { mutableStateOf("") }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = login,
                onValueChange = { login = it },
                label = { Text(text = stringResource(id = R.string.enter_login)) },
            )

            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = stringResource(id = R.string.enter_password)) },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )

            Button(
                modifier = Modifier.padding(top = 16.dp),
                onClick = {
                    eventHandler.invoke(
                        RegistrationEvent.OnRegisterUser(
                            login,
                            password
                        )
                    )
                }) {
                Text(text = stringResource(id = R.string.registration))
            }
            Text(text = stringResource(id = R.string.i_already_have_acc),
                color = Color.Blue,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {
                    eventHandler.invoke(RegistrationEvent.OnNavigate(AuthorizationScreen()))
                })
        }
    }
}

