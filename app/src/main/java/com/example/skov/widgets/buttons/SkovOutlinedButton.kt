package com.example.skov.widgets.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp

@Composable
fun SkovOutlinedButton(
    text : String,
    onClick : () -> Unit
){
    OutlinedButton(
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        border = BorderStroke(1.dp, Color.Black),
        shape = RectangleShape,
        onClick = onClick
    ) {
        Text(
            text = text
        )
    }
}