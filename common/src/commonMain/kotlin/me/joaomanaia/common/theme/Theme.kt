package me.joaomanaia.common.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import me.joaomanaia.common.getDynamicColorScheme
import me.joaomanaia.common.isDynamicColorSupported

private val LightThemeColors = lightColorScheme()

private val DarkThemeColors = darkColorScheme()

@Composable
fun GameTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    lightColorScheme: ColorScheme = LightThemeColors,
    darkColorScheme: ColorScheme = DarkThemeColors,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && isDynamicColorSupported() -> getDynamicColorScheme(darkTheme)
        darkTheme -> darkColorScheme
        else -> lightColorScheme
    }

    CompositionLocalProvider(
        LocalSpacing provides Spacing()
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            content = content
        )
    }
}
