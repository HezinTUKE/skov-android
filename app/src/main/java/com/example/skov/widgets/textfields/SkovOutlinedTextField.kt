package com.example.skov.widgets.textfields

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SkovOutlinedTextField(
    value : String,
    onValueChange : (String) -> Unit,
    minLines : Int = 1,
    maxLines : Int = 1,
    label: String,
    supportingText :  @Composable() (() -> Unit)? = null,
    leadingIcon :  @Composable() (() -> Unit)? = null,
    trailingIcon : @Composable() (() -> Unit)? = null,
    visualTransformation : VisualTransformation = VisualTransformation.None,
    keyboardOptions : KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
){

    var isFocused by remember {
        mutableStateOf(false)
    }

    OutlinedTextField(
        modifier = Modifier
            .width(300.dp)
            .onFocusChanged{
               isFocused = it.isFocused
            },
        value = value,
        onValueChange = onValueChange,
        supportingText = supportingText,
        label = {
                Text(
                    text = label,
                    color = Color.Black,
                    fontWeight =
                        if(!isFocused){
                            FontWeight.Normal
                        } else {
                            FontWeight.SemiBold
                        }
                )
        },
        leadingIcon = leadingIcon,
        minLines = minLines,
        maxLines =
            if(minLines > maxLines){
                minLines
            }else{
                 maxLines
            },
        trailingIcon = trailingIcon,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        shape = RectangleShape,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Black,
            unfocusedBorderColor = Color.Black
        )
    )
}