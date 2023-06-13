package me.joaomanaia.common.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.joaomanaia.common.model.SmallBoard
import me.joaomanaia.common.theme.spacing
import me.joaomanaia.common.ui.StatusWrapper

@Composable
@ExperimentalMaterial3Api
internal fun SmallGame(
    modifier: Modifier = Modifier,
    smallGameBoardState: SmallBoard,
    currentBoard: Boolean,
    onSquareClick: (position: Int) -> Unit
) {
    val spaceExtraSmall = MaterialTheme.spacing.extraSmall

    val color by animateColorAsState(
        targetValue = if (currentBoard) {
            MaterialTheme.colorScheme.surfaceVariant
        } else {
            MaterialTheme.colorScheme.surface
        }
    )

    val tonalElevation by animateDpAsState(
        targetValue = if (currentBoard) 8.dp else 0.dp
    )

    StatusWrapper(
        enabled = currentBoard
    ) {
        Surface(
            modifier = modifier,
            tonalElevation = tonalElevation,
            shape = MaterialTheme.shapes.medium,
            color = color
        ) {
            if (smallGameBoardState.isEnded) {
                Square(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(spaceExtraSmall),
                    state = smallGameBoardState.winner,
                    enabled = false
                )
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    verticalArrangement = Arrangement.spacedBy(spaceExtraSmall),
                    horizontalArrangement = Arrangement.spacedBy(spaceExtraSmall),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(spaceExtraSmall),
                    userScrollEnabled = false
                ) {
                    itemsIndexed(items = smallGameBoardState.squares) { index, squareState ->
                        Square(
                            state = squareState,
                            modifier = Modifier.size(50.dp),
                            enabled = currentBoard,
                            onClick = { onSquareClick(index) }
                        )
                    }
                }
            }
        }
    }
}