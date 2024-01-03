package com.example.fooddeliveryapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import cafe.adriel.voyager.navigator.Navigator
import com.example.core.designsystem.FoodDeliveryAppTheme
import com.example.feature.auth.impl.presentation.ui.RegistrationScreen

class MainActivity : ComponentActivity() {

//    private lateinit var analytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        analytics = Firebase.analytics

        setContent {
            FoodDeliveryAppTheme {
//                A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
////                    color = MaterialTheme.colorScheme.background
//                ) {
//                    val scrollBehavior =
//                        TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
//                    Scaffold(
//                        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
////                        topBar = { TopAppBar(scrollBehavior) { Navigator(HomeScreen()) } },
//
//                        ) { innerPadding ->
//                        Column(
//                            modifier = Modifier
//                                .padding(innerPadding),
//                            verticalArrangement = Arrangement.spacedBy(16.dp),
//                        ) {
//                            Text(
//                                modifier = Modifier.padding(8.dp),
//                                text =
//                                "This is an example of a scaffold.",
//                            )
//                        }
//                    }
//                }
                Navigator(screen = RegistrationScreen())
            }
        }
    }
}
