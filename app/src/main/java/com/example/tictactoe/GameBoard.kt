package com.example.tictactoe

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TicTacToeGame() {
    var boardState by remember { mutableStateOf(List(9) { "" }) }  // Flat list instead of 2D list
    var currentPlayer by remember { mutableStateOf("X") }
    var winner by remember { mutableStateOf<String?>(null) }
    var gameOver by remember { mutableStateOf(false) }

    // Function to reset the game state
    fun resetGame() {
        boardState = List(9) { "" }  // Reset the board
        currentPlayer = "X"  // Reset to player X's turn
        winner = null  // No winner
        gameOver = false  // Game not over
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Tic Tac Toe", fontSize = 32.sp)

        Spacer(modifier = Modifier.height(24.dp))

        // Board Grid
        for (row in 0..2) {
            Row {
                for (col in 0..2) {
                    val index = row * 3 + col
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .background(Color.LightGray, RoundedCornerShape(8.dp))
                            .padding(16.dp)
                            .clickable(enabled = boardState[index].isEmpty() && !gameOver) {
                                if (boardState[index] == "") {
                                    boardState = boardState.toMutableList().also {
                                        it[index] = currentPlayer  // Mark the clicked cell
                                    }
                                    winner = checkWinner(boardState)  // Check for winner
                                    if (winner != null) {
                                        gameOver = true  // Stop game if someone wins
                                    } else {
                                        currentPlayer = if (currentPlayer == "X") "O" else "X"  // Switch turns
                                    }
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = boardState[index], fontSize = 48.sp)
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Reset Button
        Button(onClick = { resetGame() }) {
            Text(text = "Reset Game")
        }

        // Show winner dialog if the game is over
        if (winner != null) {
            val resultText = if (winner == "Draw") "It's a Draw!" else "Player $winner Wins!"
            WinnerDialog(resultText, onDismiss = { resetGame() })
        }
    }
}
