package me.joaomanaia.common

import androidx.compose.material3.ColorScheme

expect fun getPlatformName(): String

expect fun isDynamicColorSupported(): Boolean

expect fun getDynamicColorScheme(darkTheme: Boolean): ColorScheme