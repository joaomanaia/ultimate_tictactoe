package me.joaomanaia.common

import androidx.compose.material3.ColorScheme

actual fun getPlatformName(): String {
    return "Desktop"
}

actual fun isDynamicColorSupported(): Boolean = false
actual fun getDynamicColorScheme(darkTheme: Boolean): ColorScheme {
    throw UnsupportedOperationException("Dynamic color is not supported on Desktop")
}