package me.joaomanaia.common.model

@JvmInline
value class UltimateBoard(
    val smallBoardState: List<SmallBoard>
) : BaseBoard {
    companion object {
        val Empty = UltimateBoard(
            smallBoardState = List(9) {
                SmallBoard.Empty
            }
        )
    }

    override val isFull: Boolean
        get() = smallBoardState.all { it.isFull }

    override val winner: SquareState
        get() {
            for (winnerLine in BaseBoard.winnerPositions) {
                // Get the first square of the board
                val firstSquare = smallBoardState[winnerLine.first()].winner

                // If the first square is not empty and is equal to the second and third square, then we have a winner
                if (firstSquare != SquareState.None && firstSquare == smallBoardState[winnerLine[1]].winner && firstSquare == smallBoardState[winnerLine[2]].winner) {
                    return firstSquare
                }
            }

            return SquareState.None
        }
}