package com.example.feature.home.impl.presentation.ui

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.core.widget.TopAppBar
import com.example.feature.home.api.model.FoodInfo
import com.example.feature.home.impl.presentation.presenter.HomeAction
import com.example.feature.home.impl.presentation.presenter.HomeEvent
import com.example.feature.home.impl.presentation.presenter.HomeScreenModel
import com.example.feature.home.impl.presentation.presenter.HomeScreenState

class HomeScreen : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel = getScreenModel<HomeScreenModel>()
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

                    HomeContent(
                        screenState = state.value,
                        eventHandler = screenModel::event
                    )

                    HomeScreenActions(
                        navigator = navigator,
                        screenAction = action
                    )
                }
            )
        }
    }
}

@Composable
fun HomeContent(
    screenState: HomeScreenState,
    eventHandler: (HomeEvent) -> Unit,
) {
    if (screenState.foods == null) {
        eventHandler.invoke(HomeEvent.OnLoadFood)
        if (screenState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentWidth(Alignment.CenterHorizontally)
                    .wrapContentHeight(Alignment.CenterVertically)
            )
        }
    } else {
        FoodList(screenState, eventHandler)
    }
}

@Composable
private fun HomeScreenActions(
    navigator: Navigator,
    screenAction: HomeAction?
) {
    val context = LocalContext.current
    LaunchedEffect(screenAction) {
        when (screenAction) {
            null -> Unit
            is HomeAction.Navigate -> {
                navigator.push(DetailsScreen(screenAction.foodId))
            }

            is HomeAction.ShowToast -> {
                Toast.makeText(context, screenAction.text, Toast.LENGTH_LONG).show()
            }
        }
    }
}

@Composable
fun FoodList(
    viewState: HomeScreenState,
    eventHandler: (HomeEvent) -> Unit,
) {

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 200.dp),
        modifier = Modifier
//            .fillMaxSize()
            .padding(top = 68.dp)
//            .background(FoodDeliveryAppTheme.colors.primaryBackground)
    ) {
        viewState.foods?.listFood?.let { list ->
            items(
                list.size,
                key = { list[it].title }) { index ->
                MyListItem(
                    foodInfo = list[index],
                    onClick = { eventHandler.invoke(HomeEvent.OnFoodClick(it)) }
                )
            }
        }
    }
}

@Composable
fun MyListItem(
    foodInfo: FoodInfo,
    onClick: (FoodInfo) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onClick.invoke(foodInfo)
            },
        elevation = CardDefaults.cardElevation(12.dp),
    ) {
//        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
        SubcomposeAsyncImage(
            model = foodInfo.imageUrl,
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
                SubcomposeAsyncImageContent(modifier = Modifier.padding(12.dp))
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp, vertical = 12.dp),
        ) {
            Text(
                text = foodInfo.title,
                textAlign = TextAlign.Center,
//                    style = MyTheme.typography.globalTextStyle
            )
            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = foodInfo.price,
                textAlign = TextAlign.Center,
//                    style = MyTheme.typography.globalTextStyle
            )
        }
    }
}
