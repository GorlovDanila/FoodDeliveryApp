package com.example.core.widget

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.Navigator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomNavigationDrawer(scrollBehavior: TopAppBarScrollBehavior, navigator: Navigator, ) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet { /* Drawer content */
                Row(
                    modifier = Modifier
                        .padding(4.dp),
                ) {
                    Text(
                        text = "This is an example of a scaffold.",
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(4.dp),
                ) {
                    Text(
                        text = "This is an example of a scaffold.",
                    )
                }
            }
        },
        content = {
            TopAppBar(
                scrollBehavior = scrollBehavior,
                navigator = navigator,
                scope = scope,
                drawerState = drawerState
            )

            Row(
                modifier = Modifier
            ) {
                Text(
                    modifier = Modifier.padding(40.dp),
                    text = "This is an example of a scaffold.",
                )
            }
            Row(
                modifier = Modifier
            ) {
                Text(
                    modifier = Modifier.padding(70.dp),
                    text = "This is an example of a scaffold.",
                )
            }
        }
    )
}


