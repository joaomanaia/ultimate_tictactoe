package me.joaomanaia.common.model

data class GameState(
    val ultimateBoardState: UltimateBoard = UltimateBoard.Empty,
    val currentUltimateBoard: Int = 0,
    val currentPlayer: SquareState = SquareState.Player1
)
