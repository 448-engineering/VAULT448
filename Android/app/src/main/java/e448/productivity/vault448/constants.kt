package e448.productivity.vault448

import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import e448.productivity.vault448.ui.theme.Typography


val padding12 = 12.dp
val padding10 = 10.dp
val padding14 = 14.dp
val padding16 = 16.dp
val padding20 = 20.dp


fun checkIsAndroidR(): Boolean {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.R
}

@Composable
fun AppVersion() {
    val packageManager = LocalContext.current.packageManager
    val packageName = LocalContext.current.packageName
    val packageInfo = packageManager.getPackageInfo(packageName, 0)
    val versionName = packageInfo.versionName

    val ffiVersion = ffiVersion()


    Column(
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(padding14)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.vault448_logo_horizontal),
                contentDescription = "VAULT448_LOGO_HORIZONTAL",
                modifier = Modifier.fillMaxWidth(.8f)
            )
        }

        Text("APP VERSION: $versionName", style = Typography.bodyLarge)
        Text("FFI VERSION: $ffiVersion", style = Typography.bodyLarge)

    }
}
