package org.example.project.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import kts_hw2.composeapp.generated.resources.Res
import kts_hw2.composeapp.generated.resources.tektur_extra_bold
import kts_hw2.composeapp.generated.resources.unispace_bd
import org.jetbrains.compose.resources.Font

data class ShuttleColours(
    val background: Color,
    val onBackground: Color,
    val container: Color,
    val onContainer: Color,
    val content: Color,
    val contentDisabled: Color,
    val gradient: List<Color>,

    val activeButtonColours: ButtonColors,
    val inactiveButtonColours: ButtonColors
) {


    companion object {
        val Dark = ShuttleColours(
            background = Color.Black,
            onBackground = Color.White,
            container = Color.White,
            onContainer = Color.Black,
            content = Color(0xFF252063),
            contentDisabled = Color(0xFF9ABAED),
            gradient = listOf(Color.Black, Color(0xFF252063)),

            activeButtonColours = ButtonColors(
                containerColor = Color(0xFF252063),
                contentColor = Color.White,
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.White
            ),
            inactiveButtonColours = ButtonColors(
                containerColor = Color(0xFF9ABAED),
                contentColor = Color.Black,
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.White
            )
        )
        val Light = ShuttleColours(
            background = Color.White,
            onBackground = Color.Black,
            container = Color.Black,
            onContainer = Color.White,
            content = Color(0xFF0033AA),
            contentDisabled = Color(0xFF8CCEFF),
            gradient = listOf(Color.White, Color(0xFF0033AA)),

            activeButtonColours = ButtonColors(
                containerColor = Color(0xFF0033AA),
                contentColor = Color.White,
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.White
            ),
            inactiveButtonColours = ButtonColors(
                containerColor = Color(0xFF8CCEFF),
                contentColor = Color.Black,
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.White
            )
        )
    }
}

data class ShuttleTypography(
    val bodyNormal: TextStyle = TextStyle(
        fontSize = 16.sp,
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal
    ),
    val bodyMedium: TextStyle = TextStyle(
        fontSize = 16.sp,
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Medium
    ),
    val bodyBold: TextStyle = TextStyle(
        fontSize = 16.sp,
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold
    )
)

private val LocalShuttleThemeColors = compositionLocalOf { ShuttleColours.Light }
private val LocalShuttleTypography = compositionLocalOf { ShuttleTypography() }

@Composable
fun ShuttleTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val customFontFamily = FontFamily(
        Font(Res.font.unispace_bd)
    )

    val typography = ShuttleTypography(
        bodyNormal = TextStyle(),
        bodyMedium = TextStyle(
            fontFamily = customFontFamily,
            fontWeight = FontWeight.Normal
        ),
        bodyBold = TextStyle(
            fontFamily = FontFamily(Font(Res.font.tektur_extra_bold)),
            fontWeight = FontWeight.ExtraBold
        )
    )
    val colors = when {
        darkTheme -> ShuttleColours.Dark
        else -> ShuttleColours.Light
    }
    CompositionLocalProvider(
        LocalShuttleThemeColors provides colors,
        LocalShuttleTypography provides typography
    ) {
        content()
    }
}

object ShuttleTheme {
    val colors: ShuttleColours
        @Composable get() = LocalShuttleThemeColors.current

    val typography: ShuttleTypography
        @Composable get() = LocalShuttleTypography.current
}

// Доделать (переписать с файла KTS (downloads) 3a45...