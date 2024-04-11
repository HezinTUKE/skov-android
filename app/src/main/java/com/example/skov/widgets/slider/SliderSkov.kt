package com.example.skov.widgets.slider

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SliderSkov(
    sliderPosition : MutableState<Int>,
    minStep : Int,
    maxStep : Int,
    steps : Int
) {
    Column {
        Slider(
            modifier = Modifier.width(225.dp),
            value = sliderPosition.value.toFloat(),
            onValueChange = { sliderPosition.value = it.toInt() },
            steps = steps,
            valueRange = minStep.toFloat()..maxStep.toFloat()
        )
        Text(text = sliderPosition.value.toString())
    }
}