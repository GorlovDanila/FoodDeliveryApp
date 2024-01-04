package com.example.feature.cart.impl.presentation.ui

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.core.widget.TopAppBar
import com.example.feature.cart.api.model.ProductInfo
import com.example.feature.cart.impl.R
import com.example.feature.cart.impl.presentation.presenter.ShoppingCartAction
import com.example.feature.cart.impl.presentation.presenter.ShoppingCartEvent
import com.example.feature.cart.impl.presentation.presenter.ShoppingCartScreenModel
import com.example.feature.cart.impl.presentation.presenter.ShoppingCartScreenState

class ShoppingCartScreen : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel = getScreenModel<ShoppingCartScreenModel>()
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

                    ShoppingCartContent(
                        screenState = state.value,
                        eventHandler = screenModel::event
                    )

                    ShoppingCartScreenActions(
                        screenAction = action
                    )
                }
            )
        }
    }
}

@Composable
fun ShoppingCartContent(
    screenState: ShoppingCartScreenState,
    eventHandler: (ShoppingCartEvent) -> Unit,
) {
    eventHandler.invoke(ShoppingCartEvent.OnLoadProducts)
    if (screenState.sumPrice == stringResource(id = R.string.nil) && screenState.products?.size == 0) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 125.dp, start = 125.dp)
        ) {
            Text(text = stringResource(id = R.string.empty_cart))
        }
    } else {
        FoodList(screenState, eventHandler)
    }
}


@Composable
private fun ShoppingCartScreenActions(
    screenAction: ShoppingCartAction?
) {
    val context = LocalContext.current
    LaunchedEffect(screenAction) {
        when (screenAction) {
            null -> Unit
            is ShoppingCartAction.ShowToast -> {
                Toast.makeText(context, screenAction.text, Toast.LENGTH_LONG).show()
            }
        }
    }
}

@Composable
fun FoodList(
    viewState: ShoppingCartScreenState,
    eventHandler: (ShoppingCartEvent) -> Unit,
) {

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 68.dp)
    ) {
        viewState.products?.let { list ->
            items(
                list.size,
                key = { list[it].title }) { index ->
                MyListItem(
                    productInfo = list[index],
                    onPlusClick = { eventHandler.invoke(ShoppingCartEvent.OnPlusProductCount(it)) },
                    onMinusClick = { eventHandler.invoke(ShoppingCartEvent.OnMinusProductCount(it)) },
                )
            }
        }
        item {
            Row(modifier = Modifier.padding(16.dp) ) {
                Button(onClick = { eventHandler.invoke(ShoppingCartEvent.OnCreateOrder) }) {
                    Row {
                        Text(text = viewState.sumPrice)
                        Text(
                            modifier = Modifier.padding(start = 24.dp),
                            text = stringResource(id = R.string.arrange)
                        )
                    }
                }
            }

        }
    }
}

@Composable
fun MyListItem(
    productInfo: ProductInfo,
    onPlusClick: (ProductInfo) -> Unit,
    onMinusClick: (ProductInfo) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(12.dp),
    ) {
        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
            SubcomposeAsyncImage(
                model = productInfo.imageUrl,
                contentDescription = null,
            ) {
                val state = painter.state
                if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
                    CircularProgressIndicator(
                        modifier = Modifier.padding(
                            vertical = 48.dp,
                            horizontal = 36.dp
                        )
                    )
                } else {
                    SubcomposeAsyncImageContent(
                        modifier = Modifier
                            .padding(12.dp)
                            .size(150.dp)
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp, vertical = 24.dp),
            ) {
                Text(
                    text = productInfo.title,
                    textAlign = TextAlign.Center,
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.padding(top = 72.dp)
                ) {
                    Text(
                        text = productInfo.price,
                        textAlign = TextAlign.Center,
                    )
                    IconButton(
                        onClick = { onMinusClick(productInfo) },
                        modifier = Modifier
                            .padding(start = 48.dp)
                            .size(36.dp)) {
                        Image(
                            painterResource(id = R.drawable.ic_minus),
                            contentDescription = null,
                        )
                    }
                    Text(
                        modifier = Modifier.padding(start = 8.dp, top = 4.dp, end = 8.dp),
                        text = productInfo.count.toString(),
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                    )
                    IconButton(
                        onClick = { onPlusClick(productInfo) },
                        modifier = Modifier
                            .size(36.dp)) {
                        Image(
                            painterResource(id = R.drawable.ic_add),
                            contentDescription = null,
                        )
                    }
                }
            }
        }
    }
}
