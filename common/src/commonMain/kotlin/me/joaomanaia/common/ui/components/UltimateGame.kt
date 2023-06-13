package me.joaomanaia.common.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.joaomanaia.common.model.GameState
import me.joaomanaia.common.theme.spacing

@Composable
@ExperimentalMaterial3Api
internal fun UltimateGame(
    modifier: Modifier = Modifier,
    gameState: GameState,
    onSquareClick: (boardPosition: Int, squarePosition: Int) -> Unit
) {
    val spaceExtraSmall = MaterialTheme.spacing.extraSmall

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(spaceExtraSmall),
        horizontalArrangement = Arrangement.spacedBy(spaceExtraSmall),
        modifier = modifier.fillMaxSize(),
        userScrollEnabled = false,
        contentPadding = PaddingValues(vertical = MaterialTheme.spacing.medium)
    ) {
        itemsIndexed(
            items = gameState.ultimateBoardState.smallBoardState
        ) { index, smallGameBoardState ->
            SmallGame(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(166.dp),
                smallGameBoardState = smallGameBoardState,
                currentBoard = gameState.currentUltimateBoard == index,
                onSquareClick = { onSquareClick(index, it) }
            )
        }
    }
}