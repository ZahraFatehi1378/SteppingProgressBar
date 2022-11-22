package com.example.stepingprogressbar.ui.progressbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CircularBottomItem(text: String , color:Color) {
    Box(
        modifier = Modifier
            .size(75.dp)
            .clip(CircleShape)
            .background(color)
            .padding(4.dp)
            .clip(CircleShape)
            .background(Color.White)
            .padding(8.dp)
            .clip(CircleShape)
            .background(color)
    ) {
        Text(
            modifier= Modifier.fillMaxSize().padding(top=5.dp),
            text = text,
            color = Color.White,
            textAlign = TextAlign.Center,
            fontSize = 28.sp
        )
    }
}