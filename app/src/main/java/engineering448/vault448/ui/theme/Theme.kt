package engineering448.vault448.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorScheme = darkColorScheme(
    primary = themeColorDarker,
    secondary = themeColorDark,
    tertiary = themeColorWhite,

    background = themeColorDark,
    surface = themeColorDark,
    onPrimary = themeColorWhite,
    onSecondary = themeColorWhite,
    onTertiary = themeColorDarker,
    onBackground = themeColorDarker,
    onSurface = themeColorWhite,
)

private val LightColorScheme = lightColorScheme(
    primary = themeColorWhite,
    secondary = themeColorWhiter,
    tertiary = themeColorDarker,

    background = themeColorWhite,
    surface = themeColorWhite,
    onPrimary = themeColorDarker,
    onSecondary = themeColorDarker,
    onTertiary = themeColorWhite,
    onBackground = themeColorWhite,
    onSurface = themeColorWhite,
)

@Composable
fun VAULT448Theme(
    content: @Composable () -> Unit
) {
    val systemUiController = rememberSystemUiController()

    systemUiController.setStatusBarColor(color = themeColorDark)
    systemUiController.setNavigationBarColor(color = themeColorDark)

    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = typography,
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