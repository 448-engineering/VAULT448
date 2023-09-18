package e448.productivity.vault448.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import e448.productivity.vault448.R
import e448.productivity.vault448.padding14

val carolloPlayscriptFont = FontFamily(
    Font(R.font.carollo_playscript_regular, FontWeight.Normal),
    Font(R.font.carollo_playscript_bold, FontWeight.Bold),
    Font(R.font.carollo_playscript_medium, FontWeight.Medium),
    Font(R.font.carollo_playscript_regular_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.carollo_playscript_bold_italic, FontWeight.Bold, FontStyle.Italic),
    Font(R.font.carollo_playscript_medium_italic, FontWeight.Medium, FontStyle.Italic)
)

val expansivaFont = FontFamily(
    Font(R.font.expansiva, FontWeight.Normal),
    Font(R.font.expansiva_bold, FontWeight.Bold),
    Font(R.font.expansiva_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.expansiva_bold_italic, FontWeight.Bold, FontStyle.Italic)
)

val typography = Typography(
    titleLarge = TextStyle(
        fontFamily = expansivaFont,
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold
    ),
    bodyLarge = TextStyle(
        fontFamily = carolloPlayscriptFont,
        fontSize = 12.sp
    ),
)

@Composable
fun FullRowCenteredTextLarge(
    textContent: String,
    textAlign: TextAlign = TextAlign.Center,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(padding14)
) {
    Row(modifier, horizontalArrangement = Arrangement.Center) {
        ExpansivaText(textContent)
    }
}


@Composable
fun ExpansivaText(
    textContent: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight = FontWeight.Bold,
    fontSize: TextUnit = 16.sp
) {

    Text(
        textContent, textAlign = textAlign, fontFamily = expansivaFont, modifier = modifier,
        fontWeight = fontWeight, fontSize = fontSize, maxLines = 1, overflow = TextOverflow.Ellipsis
    )
}