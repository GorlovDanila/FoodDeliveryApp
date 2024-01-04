package com.example.core.widget


import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.navigator.Navigator
import com.example.core.navigation.SharedScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    navigator: Navigator,
) {
    val cartScreen = rememberScreen(SharedScreen.CartScreen)
    val profileScreen = rememberScreen(SharedScreen.ProfileScreen)
    val homeScreen = rememberScreen(SharedScreen.HomeScreen)

    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(
                "Food Delivery",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        actions = {
            IconButton(
                modifier = Modifier
                    .padding(start = 16.dp),
                onClick = { navigator.push(cartScreen) }) {
                Icon(
                    imageVector = Icons.Filled.ShoppingCart,
                    contentDescription = null
                )
            }
            IconButton(onClick = { navigator.push(profileScreen) }) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = null
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = { navigator.push(homeScreen) }) {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = null
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}
