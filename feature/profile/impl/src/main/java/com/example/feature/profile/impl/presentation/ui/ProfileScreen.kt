package com.example.feature.profile.impl.presentation.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.core.navigation.SharedScreen
import com.example.core.widget.TopAppBar
import com.example.feature.profile.impl.presentation.presenter.ProfileAction
import com.example.feature.profile.impl.presentation.presenter.ProfileEvent
import com.example.feature.profile.impl.presentation.presenter.ProfileScreenModel
import com.example.feature.profile.impl.presentation.presenter.ProfileScreenState

class ProfileScreen : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel = getScreenModel<ProfileScreenModel>()
        val state = screenModel.state.collectAsStateWithLifecycle()
        val action by screenModel.action.collectAsStateWithLifecycle(null)

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val scrollBehavior =
                TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
            Scaffold(
                modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                topBar = {
                    TopAppBar(
                        scrollBehavior = scrollBehavior,
                        navigator = navigator,
                    )
                },
                content = { paddingValues ->
                    Text(
                        modifier = Modifier.padding(paddingValues),
                        text = "",
                    )

                    ProfileContent(
                        screenState = state.value,
                        eventHandler = screenModel::event
                    )

                    ProfileScreenActions(
                        navigator = navigator,
                        screenAction = action
                    )
                }
            )
        }
    }
}

@Composable
fun ProfileContent(
    screenState: ProfileScreenState,
    eventHandler: (ProfileEvent) -> Unit,
) {
    if (screenState.userInfo == null) {
        eventHandler.invoke(ProfileEvent.OnLoadUser)
    } else {
        ProfileUI(screenState, eventHandler)
    }
}

@Composable
private fun ProfileScreenActions(
    navigator: Navigator,
    screenAction: ProfileAction?
) {
    val context = LocalContext.current
    val authScreen = rememberScreen(SharedScreen.AuthorizationScreen)
    LaunchedEffect(screenAction) {
        when (screenAction) {
            null -> Unit
            is ProfileAction.Navigate -> {
                navigator.push(authScreen)
            }

            is ProfileAction.ShowToast -> {
                Toast.makeText(context, screenAction.text, Toast.LENGTH_LONG).show()
            }
        }
    }
}

@Composable
fun ProfileUI(
    viewState: ProfileScreenState,
    eventHandler: (ProfileEvent) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(top = 128.dp)
    ) {
//        Image(painter = , contentDescription = )
        viewState.userInfo?.login?.let { Text(text = it) }
        viewState.userInfo?.password?.let { Text(text = it) }
        Button(onClick = {
            eventHandler.invoke(
                ProfileEvent.OnSignOutClick
            )
        }) {
            Text(text = "Выйти")
        }
    }
}
