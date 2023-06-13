import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import me.joaomanaia.common.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "TicTacToe Ultimate"
    ) {
        App()
    }
}
