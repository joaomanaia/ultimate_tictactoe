package me.joaomanaia.common

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import me.joaomanaia.common.core.GameEngine
import me.joaomanaia.common.core.GameEngineImpl
import me.joaomanaia.common.theme.GameTheme
import me.joaomanaia.common.theme.spacing
import me.joaomanaia.common.ui.components.Square
import me.joaomanaia.common.ui.components.UltimateGame

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun App() {
    val gameEngine: GameEngine = GameEngineImpl(
        coroutineContext = SupervisorJob() + Dispatchers.IO
    )

    val gameState by gameEngine.gameState.collectAsState()

    val spaceMedium = MaterialTheme.spacing.medium

    GameTheme(darkTheme = true) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Scaffold { innerPadding ->
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .padding(spaceMedium)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Current Player: ")
                        Spacer(modifier = Modifier.width(spaceMedium))
                        Square(
                            state = gameState.currentPlayer,
                            enabled = false,
                            modifier = Modifier.size(50.dp)
                        )
                    }

                    if (gameState.ultimateBoardState.isEnded) {
                        Square(
                            state = gameState.ultimateBoardState.winner,
                            enabled = false,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(spaceMedium)
                        )
                    } else {
                        UltimateGame(
                            gameState = gameState,
                            onSquareClick = gameEngine::onSquareClick
                        )
                    }
                }
            }
        }
    }
}
