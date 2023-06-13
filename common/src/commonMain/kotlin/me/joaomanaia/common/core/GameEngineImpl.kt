package me.joaomanaia.common.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import me.joaomanaia.common.model.GameState
import me.joaomanaia.common.model.SmallBoard
import me.joaomanaia.common.model.SquareState
import me.joaomanaia.common.model.UltimateBoard
import kotlin.coroutines.CoroutineContext

class GameEngineImpl(
    override val coroutineContext: CoroutineContext
) : GameEngine, CoroutineScope {
    private val _gameState = MutableStateFlow(GameState())
    override val gameState: StateFlow<GameState>
        get() = _gameState.asStateFlow()

    override fun onSquareClick(boardPosition: Int, squarePosition: Int) {
        _gameState.update { currentState ->
            val smallBoardsState = currentState.ultimateBoardState.smallBoardState

            val newSmallBoardsState = smallBoardsState
                .toMutableList()
                .apply {
                    val selectBoardSquares = smallBoardsState[boardPosition].squares

                    val newBoardSquares = selectBoardSquares
                        .toMutableList()
                        .apply {
                            set(squarePosition, currentState.currentPlayer)
                        }

                    set(boardPosition, SmallBoard(newBoardSquares))
                }

            val newBoardPosition = if (newSmallBoardsState[boardPosition].isEnded) {
                // If the small board is full, move to the next random board
                (0..8)
                    .filterNot { newSmallBoardsState[it].isEnded }
                    .random()
            } else {
                currentState.currentUltimateBoard
            }

            currentState.copy(
                ultimateBoardState = UltimateBoard(newSmallBoardsState),
                currentUltimateBoard = newBoardPosition,
                currentPlayer = if (currentState.currentPlayer == SquareState.Player1) SquareState.Player2 else SquareState.Player1
            )
        }
    }
}