package e448.productivity.vault448


import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.app.ActivityCompat
import e448.productivity.vault448.initializer.StorageManagerPermissionsRequired
import e448.productivity.vault448.initializer.checkIsStorageManager
import e448.productivity.vault448.ui.theme.VAULT448Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                this, arrayOf(android.Manifest.permission.POST_NOTIFICATIONS), 0
            )
        }

        val currentContext: Context = this

        val nativeClass = Vault448Native()


        setContent {
            VAULT448Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {


                    val isExternalStorageManager =
                        remember { mutableStateOf(checkIsStorageManager(currentContext)) }


                    Box(
                        Modifier.safeDrawingPadding()
                    ) {
                        if (!isExternalStorageManager.value) {
                            StorageManagerPermissionsRequired(
                                storageManagerListener = isExternalStorageManager
                            )


                        } else {
                            RootUI(nativeClass)

                        }
                    }

                }
            }
        }
    }

}/*
@Composable
fun SecureElementCheck() {
    val isSecureElementPresent = remember {
        OpenMobileAPI.isSecureElementPresent()
    }
    Text(text = if (isSecureElementPresent) "This device has a secure element" else "This device does not have a secure element")
}

@Composable
fun IsStorageManager(isStorageManagerListener: MutableState<Boolean>, nativeClass: Vault448Native) {
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            @RequiresApi(Build.VERSION_CODES.R) if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                if (isStorageManagerListener.value) {
                    Foo(LocalContext.current)
                    RootUI(nativeClass)
                } else {
                    RequestExternalStorageDirPermission(isStorageManagerListener)
                }
            } else {
                CheckPermissionsAndroidPreR(LocalContext.current)
            }
        }
    }
}

*/