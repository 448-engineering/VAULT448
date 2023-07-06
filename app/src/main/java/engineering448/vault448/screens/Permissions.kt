package engineering448.vault448.screens

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

@androidx.annotation.RequiresApi(Build.VERSION_CODES.R)
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CheckPermissions() {
    //val intent = Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION)
    val pkgName = LocalContext.current.packageName
    val uri = Uri.fromParts("package", pkgName, null)
    val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION).setData(uri)

    val allFilesAccessSettingsLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = {
           
        }
    )

    val readExternalDirPermissionState = rememberPermissionState(permission = android.Manifest.permission.MANAGE_EXTERNAL_STORAGE)
    val canManageAllFiles = Environment.isExternalStorageManager()

    if (canManageAllFiles) {
        Text(text = "READ EXTERNAL GRANTED")
    }else {
        Column {
            val textToShow = if (readExternalDirPermissionState.status.shouldShowRationale) {
                "This app needs to be able to manage all external files"
            }else {
                "Camera permission required for this feature to be available. " +
                        "Please grant the permission"
            }

            Text(textToShow)

            Button(onClick = { allFilesAccessSettingsLauncher.launch(intent) }) {
                Text("Request permission")
            }
        }
    }

}
