package com.example.fooddeliveryapp.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import cafe.adriel.voyager.navigator.Navigator
import com.example.core.designsystem.FoodDeliveryAppTheme
import com.example.feature.auth.impl.presentation.ui.RegistrationScreen
import com.example.fooddeliveryapp.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import timber.log.Timber

private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { isGranted: Boolean ->
        if (isGranted) {
            Toast.makeText(this, "Notifications permission granted", Toast.LENGTH_SHORT)
                .show()
        } else {
            Toast.makeText(
                this,
                "FCM can't post notifications without POST_NOTIFICATIONS permission",
                Toast.LENGTH_LONG,
            ).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        askNotificationPermission()

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Timber.tag(TAG).w(task.exception, "Fetching FCM registration token failed")
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            // Log and toast
            val msg = getString(R.string.msg_token_fmt, token)
            Timber.tag(TAG).d(msg)
            Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
        })

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

    private fun askNotificationPermission() {
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> when {
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED -> {
                    /* FCM SDK (and your app) can post notifications */
                }

                shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS) -> {
                    /*
                    * Display an educational UI explaining to the user the features that will be enabled
                    * by them granting the POST_NOTIFICATION permission. This UI should provide the user
                    * "OK" and "No thanks" buttons. If the user selects "OK," directly request the permission.
                    * If the user selects "No thanks," allow the user to continue without notifications.
                    */
                }

                else -> {
                    /* Directly ask for the permission */
                    requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                }
            }
        }
    }
}
