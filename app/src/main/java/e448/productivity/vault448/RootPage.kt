package e448.productivity.vault448

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

@Composable
fun RootUI() {
    CenteredColumn {
        FullRowCenteredTextLarge(
            "FILE  MANAGER"
        )
        Row(modifier = Modifier.padding(padding14)) {
            InternalStorageOverview(Modifier.weight(3f))
            CustomSpacer(20.dp)
            InternalStorageCalc(Modifier.weight(1f))

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
                modifier = Modifier.fillMaxWidth(.5f)
            )
            CustomSpacer(100.dp, bottom = 20.dp)
            ExpansivaText(
                "47%",
                fontSize = 40.sp,
                fontWeight = FontWeight.Normal
            )
            CustomSpacer(100.dp, bottom = 10.dp)
            Text( "/storage/emulate/0")
        }
    }
}

@Composable
fun InternalStorageCalc(modifier: Modifier = Modifier) {
    Column(

    ) {
        Column (modifier = modifier
            .background(
                themeColorDarker, shape = RoundedCornerShape(
                    padding20
                )
            )
            .padding(padding12).weight(1f)){
            Text(text = "ONE")
        }

        Column (modifier = modifier
            .background(
                themeColorDarker, shape = RoundedCornerShape(
                    padding4
                )
            )
            .padding(padding12).weight(1f)){Text(text = "ONE")

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