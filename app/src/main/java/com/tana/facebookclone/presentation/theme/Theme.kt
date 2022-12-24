package com.tana.facebookclone.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = BrightNavyBlue,
    onPrimary = Cultured,
    background = EerieBlackNight,
    surface = EerieBlack,
    onSurface = Gainsboro,
    secondary = Green
)

private val LightColorPalette = lightColors(
    primary = BrightNavyBlue,
    onPrimary = Cultured,
    background = Cultured,
    surface = White,
    onSurface = GraniteGray,
    onBackground = EerieBlackNight,
    secondary = Green
)

@Composable
fun FacebookCloneTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}