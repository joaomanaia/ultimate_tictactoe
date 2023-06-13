package me.joaomanaia.common.core

import kotlinx.coroutines.flow.StateFlow
import me.joaomanaia.common.model.GameState

interface GameEngine {
    val gameState: StateFlow<GameState>

    fun onSquareClick(
        boardPosition: Int,
        squarePosition: Int
    )
}