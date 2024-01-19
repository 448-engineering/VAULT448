package e448.productivity.vault448

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import e448.productivity.vault448.ui.theme.Typography
import e448.productivity.vault448.ui.theme.v448ColorDeepRuby
import e448.productivity.vault448.ui.theme.v448ColorSalmon
import e448.productivity.vault448.ui.theme.v448ColorSmokyBlack


@Composable
fun CenteredColumn(
    modifier: Modifier = Modifier, widget: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        content = widget
    )
}


@Composable
fun CenteredRow(modifier: Modifier = Modifier, widget: @Composable RowScope.() -> Unit) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        content = widget
    )
}

@Composable
fun CenteredRowDarkBg(modifier: Modifier = Modifier, widget: @Composable RowScope.() -> Unit) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(padding20)
            .background(
                MaterialTheme.colorScheme.background,
                shape = RoundedCornerShape(
                    padding20
                ),
            ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        content = widget
    )
}


@Composable
fun CustomSpacer(dp: Dp = 10.dp, bottom: Dp = 0.dp) {
    Spacer(
        modifier = Modifier
            .width(dp)
            .padding(bottom = bottom)
    )
}


@Composable
fun GradientBackgroundButton(
    textContent: String, onClick: () -> Unit,
    color: Color = v448ColorSmokyBlack,
    gradientBackground: Brush = Brush.horizontalGradient(
        colors = listOf(
            v448ColorSalmon,
            v448ColorDeepRuby
        )
    ),
    style: TextStyle = Typography.labelLarge,
    shape: Shape = RoundedCornerShape(40.dp),
    horizontalPadding: Dp = 16.dp, verticalPadding: Dp = 8.dp
) {

    Text(
        text = textContent,
        color = color,
        style = style,
        modifier = Modifier
            .clickable { onClick() }
            .background(
                gradientBackground,

                shape = shape
            )
            .padding(horizontal = horizontalPadding, vertical = verticalPadding)
    )

}