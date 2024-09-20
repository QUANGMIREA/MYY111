package com.example.myapplication.presentation.common

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight


@Composable
fun NewsTextButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit

){
    TextButton(onClick = onClick,modifier= modifier, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF469E67))) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
            color = Color.White,

            )
    }
}