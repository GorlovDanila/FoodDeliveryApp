package com.example.feature.home.impl.presentation.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
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
import com.example.feature.home.api.model.FoodInfo
import com.example.feature.home.impl.presentation.presenter.DetailsAction
import com.example.feature.home.impl.presentation.presenter.DetailsEvent
import com.example.feature.home.impl.presentation.presenter.DetailsScreenModel
import com.example.feature.home.impl.presentation.presenter.DetailsScreenState

class DetailsScreen(
    private val id: Long
) : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel = getScreenModel<DetailsScreenModel>()
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

                    DetailsContent(
                        id = id,
                        viewState = state.value,
                        eventHandler = screenModel::event
                    )

                    DetailsScreenActions(
                        screenAction = action
                    )
                }
            )
        }
    }
}

@Composable
fun DetailsContent(
    id: Long,
    viewState: DetailsScreenState,
    eventHandler: (DetailsEvent) -> Unit,
) {

    if (viewState.foodInfo == null) {
        eventHandler.invoke(DetailsEvent.OnLoadFoodById(id))
        if (viewState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentWidth(Alignment.CenterHorizontally)
                    .wrapContentHeight(Alignment.CenterVertically)
            )
        }
    } else {
        DetailItem(
            viewState.foodInfo,
            eventHandler
        )
    }
}

@Composable
private fun DetailsScreenActions(
    screenAction: DetailsAction?
) {
    val context = LocalContext.current
    LaunchedEffect(screenAction) {
        when (screenAction) {
            null -> Unit
            is DetailsAction.ShowToast -> {
                Toast.makeText(context, screenAction.text, Toast.LENGTH_LONG).show()
            }
        }
    }
}

@Composable
fun DetailItem(
    foodInfo: FoodInfo?,
    eventHandler: (DetailsEvent) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 68.dp)
//        .background(MyTheme.colors.primaryBackground)
    ) {
        item {
            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    SubcomposeAsyncImage(
                        model = foodInfo?.imageUrl,
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
                            SubcomposeAsyncImageContent(modifier = Modifier.padding(vertical = 16.dp))
                        }
                    }
                }
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "${foodInfo?.title}",
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp),
//                        color = MyTheme.colors.primaryText,
//                        style = MyTheme.typography.globalTextStyle
                    )
                    Row {
                        Text(
                            text = "${foodInfo?.price}",
//                            textAlign = TextAlign.Start,
                            modifier = Modifier
                                .padding(4.dp),
//                        color = MyTheme.colors.primaryText,
//                        style = MyTheme.typography.globalTextStyle
                        )
                        Text(
                            text = "${foodInfo?.weight}",
//                            textAlign = TextAlign.Start,
                            modifier = Modifier
                                .padding(4.dp),
//                        color = MyTheme.colors.primaryText,
//                        style = MyTheme.typography.globalTextStyle
                        )
                    }
                    Text(
                        text = "${foodInfo?.recipe}",
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp),
//                        color = MyTheme.colors.primaryText,
//                        style = MyTheme.typography.globalTextStyle
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "${foodInfo?.kcal} ккал",
                            textAlign = TextAlign.Start,
                            modifier = Modifier
                                .padding(4.dp),
//                            color = MyTheme.colors.primaryText,
//                            style = MyTheme.typography.globalTextStyle
                        )
                        Text(
                            text = "${foodInfo?.proteins} белки",
                            textAlign = TextAlign.Start,
                            modifier = Modifier
                                .padding(4.dp),
//                            color = MyTheme.colors.primaryText,
//                            style = MyTheme.typography.globalTextStyle
                        )
                        Text(
                            text = "${foodInfo?.fats} жиры",
                            textAlign = TextAlign.Start,
                            modifier = Modifier
                                .padding(4.dp),
//                            color = MyTheme.colors.primaryText,
//                            style = MyTheme.typography.globalTextStyle
                        )
                        Text(
                            text = "${foodInfo?.carbohydrates} углеводы",
                            textAlign = TextAlign.Start,
                            modifier = Modifier
                                .padding(4.dp),
//                            color = MyTheme.colors.primaryText,
//                            style = MyTheme.typography.globalTextStyle
                        )
                    }
                    Text(
                        text = "* указано на 100г. блюда",
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp),
//                        color = MyTheme.colors.primaryText,
//                        style = MyTheme.typography.globalTextStyle
                    )

                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp),
                        onClick = { eventHandler.invoke(DetailsEvent.OnAddToCart) },
                        shape = RoundedCornerShape(20.dp)
                    ) {
//                        Image(
//                            painterResource(id = R.drawable.ic_cart),
//                            contentDescription = "Cart button icon",
//                            modifier = Modifier.size(20.dp)
//                        )

                        Text(
                            text = "Add to cart",
                            modifier = Modifier.padding(start = 10.dp)
                        )
                    }

//                    Text(
//                        text = "${animInfo?.synopsis}",
//                        textAlign = TextAlign.Start,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(4.dp),
//                        color = MyTheme.colors.primaryText,
//                        style = MyTheme.typography.globalTextStyle
//                    )
                }
            }
        }
    }
}
