package e448.productivity.vault448

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import e448.productivity.vault448.ui.theme.ExpansivaText
import e448.productivity.vault448.ui.theme.FullRowCenteredTextLarge
import e448.productivity.vault448.ui.theme.themeColorDarker
import e448.productivity.vault448.ui.theme.themeColorLight
import e448.productivity.vault448.*;


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RootUI(nativeClass: Vault448Native) {
    val internalStorageDetails = getStorageVolumesAccessState(LocalContext.current)

    val isSheetOpen = rememberSaveable {
        mutableStateOf(false)
    }

    CenteredColumn {
        RootModalBottomSheet(isSheetOpen, nativeClass)
        FullRowCenteredTextLarge(
            "FILE  MANAGER",
            modifier = Modifier.clickable {
                isSheetOpen.value = true
            }
        )
        Row(
            modifier = Modifier.padding(padding14),
            verticalAlignment = Alignment.CenterVertically
        ) {
            InternalStorageOverview(
                Modifier.weight(3f),
                internalStorageDetails = internalStorageDetails
            )
            CustomSpacer(20.dp)
            Column(modifier = Modifier.weight(1f)) {
                InternalStorageCalc(internalStorageDetails = internalStorageDetails)
                CustomSpacer(dp = 1.dp, bottom = 10.dp)
                FilesCalc()
            }

        }

        MediaSection()
        DataSection()
        ActionsSection()

        // Calculator as in vault and can parse speech
        // Notes
        // Converter like insect
        // SMS
        // Peek -> Can display one or a few protected docs with timer
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
        },
        sheetState = rememberModalBottomSheetState()
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(padding14)
        ) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Image(
                    painter = painterResource(id = R.drawable.vault448_logo_horizontal),
                    contentDescription = "VAULT448_LOGO_HORIZONTAL",
                    modifier = Modifier.fillMaxWidth(.8f)
                )
            }

            ExpansivaText(textContent = "APP VERSION: $versionName", fontSize = 12.sp)
            ExpansivaText(textContent = "FFI VERSION: $ffiVersion", fontSize = 12.sp)

        }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun InternalStorageOverview(
    modifier: Modifier = Modifier,
    internalStorageDetails: InternalStorageDetails
) {

    Column(
        modifier = modifier
            .background(
                themeColorDarker, shape = RoundedCornerShape(
                    padding20
                )
            )
            .padding(padding12)
    ) {
        Column(modifier = Modifier.padding(padding12)) {
            ExpansivaText(
                "INTERNAL STORAGE", fontSize = 12.sp
            )
            CustomSpacer(100.dp, bottom = 20.dp)
            Image(
                painter = painterResource(id = R.drawable.files),
                contentDescription = "Files Image",
                modifier = Modifier.fillMaxWidth(.6f)
            )
            CustomSpacer(bottom = 5.dp)
            ExpansivaText(
                "${internalStorageDetails.percentageUsed}%",
                fontSize = 40.sp,
                fontWeight = FontWeight.Normal
            )
            CustomSpacer(100.dp, bottom = 5.dp)
            Text(internalStorageDetails.path)
        }
    }
}

@Composable
fun InternalStorageCalc(
    modifier: Modifier = Modifier,
    internalStorageDetails: InternalStorageDetails
) {
    val gibTextSize = 10.sp

    Column(
        modifier = modifier
            .background(
                themeColorDarker, shape = RoundedCornerShape(
                    padding20
                )
            )
            .padding(padding12),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.phone_storage_ssd),
                contentDescription = "Files Image",
                modifier = Modifier.fillMaxWidth(1f)
            )
            CustomSpacer(100.dp, bottom = 10.dp)
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                ExpansivaText(
                    textContent = "${internalStorageDetails.usedSpace} GiB",
                    fontSize = gibTextSize
                )
                CustomSpacer(100.dp, bottom = 2.dp)
                Divider(color = themeColorLight, thickness = 1.dp)
                CustomSpacer(100.dp, bottom = 4.dp)
                ExpansivaText(
                    textContent = "${internalStorageDetails.totalSpace} GiB",
                    fontSize = gibTextSize
                )
            }
        }

    }
}


@Composable
fun FilesCalc(
    modifier: Modifier = Modifier,

    ) {

    Column(
        modifier = modifier
            .background(
                themeColorDarker, shape = RoundedCornerShape(
                    padding20
                )
            )
            .padding(padding12),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.disk_used),
                contentDescription = "Files Image",
                modifier = Modifier.fillMaxWidth(1f)
            )
            CustomSpacer(100.dp, bottom = 10.dp)
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                ExpansivaText(
                    textContent = "10M+",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal
                )
                CustomSpacer(100.dp, bottom = 5.dp)
                ExpansivaText(
                    textContent = "FILES",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

    }
}

@Composable
fun MediaSection() {
    CenteredRow(modifier = Modifier.padding(padding14)) {
        MediaSectionChild(
            icon = R.drawable.photos,
            mediaIdentifier = "Photos",
            mediaCount = "20",
            modifier = Modifier.weight(1f)
        )
        MediaSectionChild(
            icon = R.drawable.videos,
            mediaIdentifier = "Videos",
            mediaCount = "301",
            modifier = Modifier.weight(1f)
        )
        MediaSectionChild(
            icon = R.drawable.electric_guitar,
            mediaIdentifier = "Music",
            mediaCount = "1,000+",
            modifier = Modifier.weight(1f)
        )
        MediaSectionChild(
            icon = R.drawable.documents,
            mediaIdentifier = "Documents",
            mediaCount = "100+",
            modifier = Modifier.weight(1f)
        )

    }
}

@Composable
fun DataSection() {
    CenteredRow(modifier = Modifier.padding(padding14)) {
        Box(
            modifier = Modifier
                .background(
                    themeColorDarker, shape = RoundedCornerShape(
                        padding20
                    )
                )
                .weight(1.2f),
            contentAlignment = Alignment.Center
        ) {
            CenteredRow(
                modifier = Modifier
                    .padding(padding10)
            ) {
                DataSectionChild(
                    modifier = Modifier
                        .weight(1f),
                    icon = R.drawable.trash,
                    mediaIdentifier = "Trash"
                )

                DataSectionChild(
                    modifier = Modifier.weight(1f),
                    icon = R.drawable.graph,
                    mediaIdentifier = "Analyze"
                )

                DataSectionChild(
                    modifier = Modifier.weight(1f),
                    icon = R.drawable.extras,
                    mediaIdentifier = "Extras"
                )
            }
        }


        Box(
            modifier = Modifier
                .padding(padding10)
                .background(
                    themeColorDarker, shape = RoundedCornerShape(
                        padding20
                    )
                )
                .weight(0.6f),
            contentAlignment = Alignment.Center
        ) {
            CenteredRow{
                DataSectionChildNoDescription(
                    modifier = Modifier.weight(1f),
                    icon = R.drawable.favourites,
                    mediaIdentifier = "Favourite"
                )

                DataSectionChildNoDescription(
                    modifier = Modifier.weight(1f),
                    icon = R.drawable.download,
                    mediaIdentifier = "Downloads"
                )
            }
        }

    }
}


@Composable
fun MediaSectionChild(
    icon: Int,
    mediaIdentifier: String,
    mediaCount: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(padding4)
            .aspectRatio(1f)
            .background(
                themeColorDarker, shape = RoundedCornerShape(
                    20.dp
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        CenteredColumn(modifier = Modifier.padding(padding8)) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = "$mediaIdentifier icon",
                modifier = Modifier.fillMaxWidth(0.4f)
            )
            CustomSpacer(bottom = 5.dp)
            Text(text = mediaIdentifier, fontSize = 12.sp)
            CustomSpacer(bottom = 5.dp)
            ExpansivaText(textContent = mediaCount, fontSize = 12.sp)
        }
    }
}

@Composable
fun DataSectionChild(
    modifier: Modifier = Modifier,
    icon: Int, mediaIdentifier: String
) {
    Box(
        modifier = modifier.padding(padding4)
    ) {
        CenteredColumn(modifier = Modifier.padding(2.dp)) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = "$mediaIdentifier icon",
                modifier = Modifier.fillMaxWidth(0.4f)
            )
            CustomSpacer(bottom = 5.dp)
            Text(text = mediaIdentifier, fontSize = 12.sp)
        }
    }
}


@Composable
fun DataSectionChildNoDescription(
    modifier: Modifier = Modifier,
    icon: Int, mediaIdentifier: String
) {
    Box(
        modifier = modifier.padding(padding8)
    ) {
        CenteredColumn(modifier = Modifier.padding(2.dp)) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = "$mediaIdentifier icon",
                modifier = Modifier.fillMaxWidth(0.8f)
            )
            CustomSpacer(bottom = 5.dp)
        }
    }
}

@Composable
fun ActionsSection() {
    CenteredRow {
        CenteredRow(modifier = Modifier.weight(1f)) {
            CenteredColumn {

            }
        }
        CenteredRow(modifier = Modifier.weight(5f)) {
            CenteredColumn {

            }
        }
    }
}
