package e448.productivity.vault448

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun CenteredColumn(widget: @Composable () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        widget()
    }
}


@Composable
fun CenteredRow(widget: @Composable () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween ,
        verticalAlignment = Alignment.CenterVertically
    ) {
        widget()
    }
}

@Composable
fun CustomSpacer(dp: Dp, bottom: Dp = 0.dp) {
    Spacer(modifier = Modifier.width(dp).padding(bottom = bottom))
}