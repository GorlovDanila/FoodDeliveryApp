package com.example.feature.home.impl.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.core.widget.CustomNavigationDrawer

class HomeScreen : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val scaffoldState = rememberBottomSheetScaffoldState()
        val scope = rememberCoroutineScope()
        Surface(
            modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
        ) {
            val scrollBehavior =
                TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
            Scaffold(
                modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                topBar = {
                    CustomNavigationDrawer(
                        scrollBehavior = scrollBehavior,
                        navigator = navigator
                    )
                },
                content = {paddingValues ->
                    Text(
                            modifier = Modifier.padding(paddingValues),
                            text = "This is an example of a scaffold.",
                        )}
//                content = { innerPadding ->
//                    Column(
//                        modifier = Modifier
//                            .padding(innerPadding),
//                        verticalArrangement = Arrangement.spacedBy(16.dp),
//                    ) {
//                        Text(
//                            modifier = Modifier.padding(120.dp),
//                            text = "This is an example of a scaffold.",
//                        )
//                    }
//                    Row(
//                        modifier = Modifier
//                            .padding(innerPadding),
//                    ) {
//                        Text(
//                            modifier = Modifier.padding(120.dp),
//                            text = "This is an example of a scaffold.",
//                        )
//                    }
//                    Row(
//                        modifier = Modifier
//                            .padding(innerPadding),
//                    ) {
//                        Text(
//                            modifier = Modifier.padding(120.dp),
//                            text = "This is an example of a scaffold.",
//                        )
//                    }
//                }
                    )
                }
        }
    }
