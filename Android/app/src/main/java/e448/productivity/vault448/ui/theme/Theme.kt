package e448.productivity.vault448.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = v448ColorChineseBlack,
    secondary = v448ColorChineseBlack,
    tertiary = v448ColorWhite,

    surface = v448ColorEerieBlack,
    background = v448ColorChineseBlack ,
    onPrimary = v448ColorWhite,
    onTertiary = v448ColorChineseBlack,
    onBackground = v448ColorWhite,
    onSurface = v448ColorWhite,
)

private val LightColorScheme = lightColorScheme(
    primary = v448ColorWhite,
    secondary = v448ColorChineseBlack,
    tertiary = v448ColorChineseBlack,

    background = v448ColorWhite,
    surface = v448ColorWhite,
    onPrimary = v448ColorChineseBlack,
    onSecondary = v448ColorWhite,
    onTertiary = v448ColorWhite,
    onBackground = v448ColorChineseBlack,
    onSurface = v448ColorChineseBlack,
)

@Composable
fun VAULT448Theme(
    content: @Composable () -> Unit
) {
    //val systemUiController = rememberSystemUiController()

    //systemUiController.setStatusBarColor(color = themeColorDark)
    //systemUiController.setNavigationBarColor(color = themeColorDark)

    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}


/*@Composable
fun VAULT448Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}*/


@Composable
fun VAULT448Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}