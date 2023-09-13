package e448.productivity.vault448

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState;
import android.provider.Settings
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat




@Composable
fun CheckPermissionsAndroidPreR(context: Context) {
    if (ContextCompat.checkSelfPermission(
            context, android.Manifest.permission.MANAGE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_DENIED || ContextCompat.checkSelfPermission(
            context, android.Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_DENIED || ContextCompat.checkSelfPermission(
            context, android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_DENIED
    ) {

    }
}

@Composable
fun RequestPermission_vR() {

}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RequestExternalStorageDirPermission(storageListener: MutableState<Boolean>) {
    val pkgName = LocalContext.current.packageName
    val uri = Uri.fromParts("package", pkgName, null)
    val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION).setData(uri)


    val allFilesAccessSettingsLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = {
            storageListener.value = checkIsExternalStorageManager()
        }
    )

    Column {
        Text(text = "This app requires access to all files. Please give permission")

        Button(onClick = {
            allFilesAccessSettingsLauncher.launch(intent)
        }) {
            Text(text = "GIVE PERMISSION")
        }
    }

}


fun checkIsExternalStorageManager() : Boolean{
    @RequiresApi(Build.VERSION_CODES.R)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        return Environment.isExternalStorageManager()
    } else {
        // TODO
         return false
    }
}