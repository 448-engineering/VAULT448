package e448.productivity.vault448.fileManager

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import e448.productivity.vault448.CustomSpacer
import e448.productivity.vault448.R
import e448.productivity.vault448.padding10
import e448.productivity.vault448.padding12
import e448.productivity.vault448.padding20
import e448.productivity.vault448.ui.theme.expansivaFont
import e448.productivity.vault448.ui.theme.v448ColorDeepRuby


@Composable
fun InternalStorageOverview(
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier
            .background(
                MaterialTheme.colorScheme.background, shape = RoundedCornerShape(
                    padding20
                )
            )
            .padding(padding10)
    ) {
        Column(modifier = Modifier.padding(padding12)) {
            Text(
                "INTERNAL STORAGE", style = MaterialTheme.typography.labelLarge
            )
            CustomSpacer(100.dp, bottom = 20.dp)
            Image(
                painter = painterResource(id = R.drawable.files),
                contentDescription = "Files Image",
                modifier = Modifier.fillMaxWidth(.6f)
            )
            CustomSpacer(bottom = 5.dp)
            Text(
                "44%",
                style = TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = 44.sp,
                    fontFamily = expansivaFont,

                ),
                overflow = TextOverflow.Ellipsis
            )
            CustomSpacer(100.dp, bottom = 5.dp)
            Text("/storage/emulated/0", style = MaterialTheme.typography.bodyLarge, overflow = TextOverflow.Ellipsis)
        }
    }
}

@Composable
fun InternalStorageCalc(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .background(
                MaterialTheme.colorScheme.background, shape = RoundedCornerShape(
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
                Text(
                    text = "125 GiB",
                    style = MaterialTheme.typography.labelLarge
                )
                CustomSpacer(100.dp, bottom = 2.dp)
                Divider(color = v448ColorDeepRuby, thickness = 1.dp)
                CustomSpacer(100.dp, bottom = 4.dp)
                Text(
                    text = "256 GiB",
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }

    }
}


@Composable
fun FilesCalc(
    modifier: Modifier = Modifier,
    //outcome: MutableState<DirOutcome>
) {

    Column(
        modifier = modifier
            .background(
                MaterialTheme.colorScheme.background, shape = RoundedCornerShape(
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
                Text(
                    "100",
                    style = MaterialTheme.typography.titleMedium
                )
                CustomSpacer(100.dp, bottom = 5.dp)
                Text(
                    text = "FILES",
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }

    }
}