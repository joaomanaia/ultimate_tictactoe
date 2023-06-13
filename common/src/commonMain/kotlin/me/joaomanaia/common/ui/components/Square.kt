package me.joaomanaia.common.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import me.joaomanaia.common.model.SquareState
import me.joaomanaia.common.theme.GameTheme

@Composable
@ExperimentalMaterial3Api
internal fun Square(
    modifier: Modifier = Modifier,
    state: SquareState = SquareState.None,
    enabled: Boolean = true,
    onClick: () -> Unit = {}
) {
    val color by animateColorAsState(
        targetValue = when (state) {
            SquareState.None -> MaterialTheme.colorScheme.surface
            SquareState.Player1 -> MaterialTheme.colorScheme.secondary
            SquareState.Player2 -> MaterialTheme.colorScheme.tertiary
        },
        label = "ContainerColorAnimation"
    )

    Surface(
        shape = MaterialTheme.shapes.medium,
        tonalElevation = 8.dp,
        modifier = modifier,
        onClick = onClick,
        color = color,
        enabled = !state.isPlayed() && enabled
    ) {
        if (state.isPlayed()) {
            SquareIcon(
                state = state
            )
        }
    }
}

@Composable
private fun SquareIcon(
    modifier: Modifier = Modifier,
    state: SquareState
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = state.icon,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview
@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun SquarePreview() {
    GameTheme {
        Surface {
            Square(
                onClick = {}
            )
        }
    }
}
