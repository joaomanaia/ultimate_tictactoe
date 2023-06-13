package me.joaomanaia.common.model

@JvmInline
value class SmallBoard(
    val squares: List<SquareState>
) : BaseBoard {
    companion object {
        val Empty = SmallBoard(
            squares = List(9) {
                SquareState.None
            }
        )
    }

    override val isFull: Boolean
        get() = squares.all { it != SquareState.None }

    /**
     * Gets the winner of the board.
     * Checks if there is a winner in any of the possible winning lines.
     */
    override val winner: SquareState
        get() {
            for (winnerLine in BaseBoard.winnerPositions) {
                // Get the first square of the board
                val firstSquare = squares[winnerLine.first()]

                // If the first square is not empty and is equal to the second and third square, then we have a winner
                if (firstSquare != SquareState.None && firstSquare == squares[winnerLine[1]] && firstSquare == squares[winnerLine[2]]) {
                    return firstSquare
                }
            }

            return SquareState.None
        }
}
