package me.joaomanaia.common

import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.ui.platform.LocalContext

actual fun getPlatformName(): String {
    return "Android"
}

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S)
actual fun isDynamicColorSupported(): Boolean {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
}

actual fun getDynamicColorScheme(darkTheme: Boolean): ColorScheme {
    val context = LocalContext.current

    return if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
}