package e448.productivity.vault448

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import e448.productivity.vault448.fileManager.FilesCalc
import e448.productivity.vault448.fileManager.InternalStorageCalc
import e448.productivity.vault448.fileManager.InternalStorageOverview
import e448.productivity.vault448.ui.theme.Typography


@Composable
fun RootUI(nativeClass: Vault448Native) {
    val isSheetOpen = rememberSaveable {
        mutableStateOf(false)
    }
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier.verticalScroll(state = scrollState)
    ) {
        CenteredColumn {
            RootModalBottomSheet(isSheetOpen, nativeClass)
            CenteredRow {
                Text(
                    "FILE  MANAGER",
                    style = Typography.titleMedium,
                    modifier = Modifier.clickable {
                        isSheetOpen.value = true
                    })
            }
            Row(
                modifier = Modifier.padding(padding10),
                verticalAlignment = Alignment.CenterVertically
            ) {
                InternalStorageOverview(
                    Modifier.weight(3f),
                )
                CustomSpacer(10.dp)
                Column(modifier = Modifier.weight(1f)) {
                    InternalStorageCalc()
                    CustomSpacer(dp = 1.dp, bottom = 10.dp)
                    FilesCalc()
                }

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootModalBottomSheet(isSheetOpen: MutableState<Boolean>, nativeClass: Vault448Native) {
    val packageManager = LocalContext.current.packageManager
    val packageName = LocalContext.current.packageName
    val packageInfo = packageManager.getPackageInfo(packageName, 0)
    val versionName = packageInfo.versionName

    val ffiVersion = ffiVersion()

    if (isSheetOpen.value) {
        ModalBottomSheet(
            onDismissRequest = {
                isSheetOpen.value = false
            }, sheetState = rememberModalBottomSheetState()
        ) {
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

                Text("App Version: $versionName", style = Typography.labelLarge)
                Text("FFI Version: $ffiVersion", style = Typography.labelLarge)

            }
        }
    }
}



