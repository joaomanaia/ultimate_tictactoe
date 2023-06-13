package me.joaomanaia.common.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha

@Composable
internal fun StatusWrapper(
    enabled: Boolean = true,
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier.alpha(if (enabled) 1.00f else 0.38f)
    ) {
        content()
    }
}
