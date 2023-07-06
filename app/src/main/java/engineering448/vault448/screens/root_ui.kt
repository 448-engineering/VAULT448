package engineering448.vault448.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import engineering448.vault448.padding12
import engineering448.vault448.ui.theme.FullRowCenteredTextLarge
import engineering448.vault448.ui.theme.VAULT448Theme
import engineering448.vault448.ui.theme.themeColorDarker

@Composable
fun RootUI() {
    CenteredColumn {
        FullRowCenteredTextLarge(
            "FILE  MANAGER"
        )
        StorageParcentage()
    }
}

@Composable
fun StorageParcentage() {
    Column(modifier = Modifier.background(themeColorDarker).padding(padding12)) {
        
    }
}

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

@Preview(showBackground = true)
@Composable
fun RootUIPreview() {
    VAULT448Theme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background

        ) {
            RootUI()
        }
    }
}
