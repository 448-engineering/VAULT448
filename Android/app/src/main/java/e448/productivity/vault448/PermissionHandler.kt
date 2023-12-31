package e448.productivity.vault448

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.MutableState
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