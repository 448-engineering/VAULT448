package e448.productivity.vault448.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = carolloPlayscriptFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

    labelSmall = TextStyle(
        fontFamily = carolloPlayscriptFont,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),

    labelMedium = TextStyle(
        fontFamily = carolloPlayscriptFont,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),

    labelLarge = TextStyle(
        fontFamily = carolloPlayscriptFont,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),

    titleLarge = TextStyle(
        fontFamily = expansivaFont,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),

    titleMedium = TextStyle(
        fontFamily = expansivaFont,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),

    titleSmall = TextStyle(
        fontFamily = expansivaFont,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    )

)

/*

Typography(
    val displayLarge: TextStyle = COMPILED_CODE,
    val displayMedium: TextStyle = COMPILED_CODE,
    val displaySmall: TextStyle = COMPILED_CODE,
    val headlineLarge: TextStyle = COMPILED_CODE,
    val headlineMedium: TextStyle = COMPILED_CODE,
    val headlineSmall: TextStyle = COMPILED_CODE,
    val titleLarge: TextStyle = COMPILED_CODE,
    val titleMedium: TextStyle = COMPILED_CODE,
    val titleSmall: TextStyle = COMPILED_CODE,
    val bodyLarge: TextStyle = COMPILED_CODE,
    val bodyMedium: TextStyle = COMPILED_CODE,
    val bodySmall: TextStyle = COMPILED_CODE,
    val labelLarge: TextStyle = COMPILED_CODE,
    val labelMedium: TextStyle = COMPILED_CODE,
    val labelSmall: TextStyle = COMPILED_CODE
)
 */

