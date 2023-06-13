package me.joaomanaia.common.model

sealed class SquareState(
    val icon: String
) {
    object None : SquareState("")

    object Player1 : SquareState("X")

    object Player2 : SquareState("O")

    fun isPlayed() = this != None

    override fun toString(): String = icon
}