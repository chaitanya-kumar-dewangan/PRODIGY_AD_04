package com.example.tictactoe.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun TicTacToeTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = Color(0xFF6200EE),
            onPrimary = Color.White,
            surface = Color(0xFFF1F1F1),
            onSurface = Color.Black
        ),
        typography = Typography(),
        content = content
    )
}
