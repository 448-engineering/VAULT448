package e448.productivity.vault448


import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import e448.productivity.vault448.ui.theme.VAULT448Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        val nativeClass = Vault448Native()

        setContent {
            VAULT448Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    val isExternalStorageManager =
                        remember { mutableStateOf(checkIsExternalStorageManager()) }

                    IsStorageManager(isExternalStorageManager,nativeClass)

                }
            }
        }
    }
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

