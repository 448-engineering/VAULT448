package e448.productivity.vault448

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import e448.productivity.vault448.ui.theme.v448ColorSalmon


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
            mediaIdentifier = "Docs",
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
                    MaterialTheme.colorScheme.background, shape = RoundedCornerShape(
                        padding20
                    )
                )
                .weight(1.2f), contentAlignment = Alignment.Center
        ) {
            CenteredRow(
                modifier = Modifier.padding(padding10)
            ) {
                DataSectionChild(
                    modifier = Modifier.weight(1f),
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
                    MaterialTheme.colorScheme.background, shape = RoundedCornerShape(
                        padding20
                    )
                )
                .weight(0.6f), contentAlignment = Alignment.Center
        ) {
            CenteredRow {
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
    icon: Int, mediaIdentifier: String, mediaCount: String, modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(padding4)
            .background(
                MaterialTheme.colorScheme.background, shape = RoundedCornerShape(
                    padding16
                )
            ), contentAlignment = Alignment.Center
    ) {
        CenteredColumn(modifier = Modifier.padding(
            horizontal = 1.dp, vertical = padding16
        )) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = "$mediaIdentifier icon",
                modifier = Modifier.fillMaxWidth(0.4f)
            )
            CustomSpacer(bottom = 5.dp)
            Text(text = mediaIdentifier, style = MaterialTheme.typography.bodyMedium)
            CustomSpacer(bottom = 5.dp)
            Text(text = mediaCount, style = MaterialTheme.typography.labelLarge)
        }
    }
}

@Composable
fun DataSectionChild(
    modifier: Modifier = Modifier, icon: Int, mediaIdentifier: String
) {
    Box(
        modifier = modifier.padding(padding4)
    ) {
        CenteredColumn() {
            Image(
                painter = painterResource(id = icon),
                contentDescription = "$mediaIdentifier icon",
                modifier = Modifier.fillMaxWidth(0.4f)
            )
            CustomSpacer(bottom = 5.dp)
            Text(text = mediaIdentifier, style = MaterialTheme.typography.bodyMedium)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DataSectionChildNoDescription(
    modifier: Modifier = Modifier, icon: Int, mediaIdentifier: String
) {
    val notificationColor = remember { mutableStateOf(v448ColorSalmon) }
    Box(
        modifier = modifier.padding(padding10)
    ) {
        CenteredColumn() {
            BadgedBox(modifier = Modifier
                .padding(0.dp)
                .background(Color.Transparent), badge = {
                Badge(
                    modifier = Modifier
                        .padding(0.dp)
                        .size(4.dp),
                    containerColor = notificationColor.value
                )
            }) {

                Image(
                    painter = painterResource(id = icon),
                    contentDescription = "$mediaIdentifier icon",
                    modifier = Modifier.fillMaxWidth(0.8f)
                )
            }
            CustomSpacer(bottom = 5.dp)
        }
    }
}
