package e448.productivity.vault448.initializer

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import e448.productivity.vault448.AppVersion
import e448.productivity.vault448.CenteredColumn
import e448.productivity.vault448.CenteredRow
import e448.productivity.vault448.CenteredRowDarkBg
import e448.productivity.vault448.CustomSpacer
import e448.productivity.vault448.GradientBackgroundButton
import e448.productivity.vault448.checkIsAndroidR
import e448.productivity.vault448.padding16
import e448.productivity.vault448.ui.theme.v448ColorDeepPeach


@Composable
fun StorageManagerPermissionsRequired(
    storageManagerListener: MutableState<Boolean>
) {
    CenteredColumn {
        CenteredRow {
            AppVersion()
        }

        CenteredRow {
            if (checkIsAndroidR()) {
                StorageManagerPermissionRequest(storageManagerListener)
            } else {
                PreStorageManagerPermissionRequest()
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun StorageManagerPermissionRequest(storageListener: MutableState<Boolean>) {
    val pkgName = LocalContext.current.packageName
    val uri = Uri.fromParts("package", pkgName, null)
    val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION).setData(uri)


    val allFilesAccessSettingsLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult(),
            onResult = {
                storageListener.value = Environment.isExternalStorageManager()
            })

    Column {
        CenteredRowDarkBg {
            CenteredRow(modifier = Modifier.padding(padding16)) {
                Text(
                    textAlign = TextAlign.Justify,
                    color = v448ColorDeepPeach,
                    text = "To manage all files, Android requires this app to have the 'Access All Files' permission to be enabled."
                )
            }
        }
        CustomSpacer(bottom = 20.dp)
        CenteredRow {
            GradientBackgroundButton("Allow Access to All Files", onClick = {
                allFilesAccessSettingsLauncher.launch(intent)
            })
        }
    }
}


@Composable
fun PreStorageManagerPermissionRequest() {
    val context = LocalContext.current

    val permanentlyDenied =
        remember { mutableStateOf(checkIsStoragePermissionPermanentlyDenied(context)) }

    val storagePermissionsLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestMultiplePermissions(),
            onResult = { permissionStatuses ->

                permissionStatuses.forEach { keyValue ->
                    // Check if the permission was permanently denied
                    val showRationale = ActivityCompat.shouldShowRequestPermissionRationale(
                        context as ComponentActivity, keyValue.key
                    )

                    // If permission is not granted and not permanently denied, request it
                    if (!showRationale) {
                        permanentlyDenied.value = true
                    }

                }
            })

    if (permanentlyDenied.value) {
        Column {
            CenteredRowDarkBg {
                CenteredRow(modifier = Modifier.padding(padding16)) {
                    Text(
                        textAlign = TextAlign.Center,
                        color = v448ColorDeepPeach,
                        text = "Android requires this app to be allowed to manage internal and external storage. However you have permanently declined some permissions. Manually grant storage permissions in the settings and Restart the App!"
                    )
                }
            }
            CustomSpacer(bottom = 20.dp)
            CenteredRow {
                GradientBackgroundButton("Manually Allow Permissions", onClick = {
                    val intent = Intent(
                        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.fromParts("package", context.packageName, null)
                    )
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(context, intent, null)
                })
            }
        }
    } else {
        Column {
            CenteredRowDarkBg {
                CenteredRow(modifier = Modifier.padding(padding16)) {
                    Text(
                        textAlign = TextAlign.Center,
                        color = v448ColorDeepPeach,
                        text = "Android requires this app to be allowed to manage internal and external storage."
                    )
                }
            }
            CustomSpacer(bottom = 20.dp)
            CenteredRow {
                GradientBackgroundButton("Allow App Permissions", onClick = {
                    storagePermissionsLauncher.launch(
                        arrayOf(
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                        )
                    )
                })
            }
        }
    }
}


fun checkIsStorageManager(context: Context): Boolean {
    return if (checkIsAndroidR()) {
        Environment.isExternalStorageManager()
    } else {
        ContextCompat.checkSelfPermission(
            context, Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
            context, Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }
}

fun checkIsStoragePermissionPermanentlyDenied(context: Context): Boolean {
    // Check if the permission was permanently denied
    val read = ActivityCompat.shouldShowRequestPermissionRationale(
        context as ComponentActivity, Manifest.permission.READ_EXTERNAL_STORAGE
    )

    val write = ActivityCompat.shouldShowRequestPermissionRationale(
        context, Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    return !(!read || !write)
}


