package com.example.tictactoe

fun checkWinner(boardState: List<String>): String? {
    // Check rows
    for (i in 0 until 9 step 3) {
        if (boardState[i] == boardState[i + 1] && boardState[i + 1] == boardState[i + 2] && boardState[i] != "") {
            return boardState[i]
        }
    }

    // Check columns
    for (i in 0..2) {
        if (boardState[i] == boardState[i + 3] && boardState[i + 3] == boardState[i + 6] && boardState[i] != "") {
            return boardState[i]
        }
    }

    // Check diagonals
    if (boardState[0] == boardState[4] && boardState[4] == boardState[8] && boardState[0] != "") {
        return boardState[0]
    }
    if (boardState[2] == boardState[4] && boardState[4] == boardState[6] && boardState[2] != "") {
        return boardState[2]
    }

    // Check for draw
    if (boardState.all { it.isNotEmpty() }) {
        return "Draw"
    }

    return null
}
