package e448.productivity.vault448

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import e448.productivity.vault448.ui.theme.ExpansivaText
import e448.productivity.vault448.ui.theme.FullRowCenteredTextLarge
import e448.productivity.vault448.ui.theme.VAULT448Theme
import e448.productivity.vault448.ui.theme.themeColorDarker
import e448.productivity.vault448.ui.theme.themeColorLight

@Composable
fun RootUI() {
    CenteredColumn {
        FullRowCenteredTextLarge(
            "FILE  MANAGER"
        )
        Row(
            modifier = Modifier.padding(padding14),
            verticalAlignment = Alignment.CenterVertically
        ) {
            InternalStorageOverview(Modifier.weight(3f))
            CustomSpacer(20.dp)
            Column(modifier = Modifier.weight(1f)) {
                InternalStorageCalc()
                CustomSpacer(dp = 1.dp, bottom = 10.dp)
                FilesCalc()
            }

        }
    }
}

@Composable
fun InternalStorageOverview(modifier: Modifier = Modifier) {
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
            CustomSpacer(100.dp, bottom = 20.dp)
            ExpansivaText(
                "47%",
                fontSize = 40.sp,
                fontWeight = FontWeight.Normal
            )
            CustomSpacer(100.dp, bottom = 10.dp)
            Text("/storage/emulate/0")
        }
    }
}

@Composable
fun InternalStorageCalc(
    modifier: Modifier = Modifier,

    ) {
    val gibTextSize = 10.sp;

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
                ExpansivaText(textContent = "40GiB", fontSize = gibTextSize)
                CustomSpacer(100.dp, bottom = 2.dp)
                Divider(color = themeColorLight, thickness = 1.dp)
                CustomSpacer(100.dp, bottom = 4.dp)
                ExpansivaText(textContent = "256GiB", fontSize = gibTextSize)
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

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    VAULT448Theme {
        Scaffold { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                RootUI()
            }
        }
    }


}