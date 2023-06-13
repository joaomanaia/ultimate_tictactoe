package me.joaomanaia.common.model

interface BaseBoard {
    val isFull: Boolean

    val isEnded: Boolean
        get() = winner != SquareState.None || isFull

    val winner: SquareState

    companion object {
        val winnerPositions = listOf(
            listOf(0, 1, 2),
            listOf(3, 4, 5),
            listOf(6, 7, 8),
            listOf(0, 3, 6),
            listOf(1, 4, 7),
            listOf(2, 5, 8),
            listOf(0, 4, 8),
            listOf(2, 4, 6)
        )
    }
}