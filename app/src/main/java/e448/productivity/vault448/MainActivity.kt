package e448.productivity.vault448


import android.content.Intent
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.work.WorkInfo
import e448.productivity.vault448.ui.theme.VAULT448Theme
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import java.util.logging.Handler


class MainActivity : ComponentActivity() {
    private lateinit var storageDetailsViewModel: StorageDetailsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        val nativeClass = Vault448Native()

        // Initialize ViewModel for LiveData
        storageDetailsViewModel = ViewModelProvider(this)[StorageDetailsViewModel::class.java]








        setContent {
            VAULT448Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    val isExternalStorageManager =
                        remember { mutableStateOf(checkIsExternalStorageManager()) }

                    IsStorageManager(isExternalStorageManager,nativeClass, storageDetailsViewModel, this)

                }
            }
        }
    }


}



// Trigger a refresh of data when needed
private fun refreshData( storageDetailsViewModel: StorageDetailsViewModel) {
    storageDetailsViewModel.refreshData()
}

@Composable
fun IsStorageManager(isStorageManagerListener: MutableState<Boolean>, nativeClass: Vault448Native,
                     storageDetailsViewModel: StorageDetailsViewModel,
                     lifecycleOwner :LifecycleOwner
) {
    val internalStorageDetails = remember { mutableStateOf(
        initInternalStorageDetails ()
    ) }

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            @RequiresApi(Build.VERSION_CODES.R) if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                if (isStorageManagerListener.value) {
                    // Observe LiveData
                    storageDetailsViewModel.storageDetailsLiveData.observe(lifecycleOwner) { workInfoList ->
                        val workInfo = workInfoList.firstOrNull()
                        if (workInfo != null && workInfo.state == WorkInfo.State.SUCCEEDED) {

                            internalStorageDetails.value.volumeDescription = workInfo.outputData.getString("volumeDescription").toString()
                            internalStorageDetails.value.percentageUsed = workInfo.outputData.getLong("percentageUsed", 0L).toULong()
                            internalStorageDetails.value.freeSpace =
                                workInfo.outputData.getLong("freeSpace", 0L).toULong()
                            internalStorageDetails.value.usedSpace = workInfo.outputData.getLong("usedSpace", 0L).toULong()
                            //internalStorageDetails.value.totalSpace = workInfo.outputData.getLong("totalSpace", 0L).toULong()
                            internalStorageDetails.value.path = workInfo.outputData.getString("path", ).toString()


                            refreshData(storageDetailsViewModel)
                        } else {
                            // FIXME Handle other states if needed (FAILED, CANCELLED, etc.)
                        }
                    }

                    RootUI(nativeClass, internalStorageDetails)
                } else {
                    RequestExternalStorageDirPermission(isStorageManagerListener)
                }
            } else {
                CheckPermissionsAndroidPreR(LocalContext.current)
            }
        }
    }
}

