package com.example.stepingprogressbar.ui.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun BottomNavGraph(
    navHostController: NavHostController,
    screensList: MutableList<String>,
) {

    NavHost(
        navController = navHostController,
        startDestination = screensList[0]
    ) {
        screensList.forEach { r ->
            composable(
                route = r,
            ) {
                FakeScreen(r)
            }
        }

    }
}


@Composable
fun FakeScreen(text: String) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .height(240.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(modifier=Modifier.fillMaxWidth(),
            text = text,
            color = Color(0xFF00A693),
            style = MaterialTheme.typography.h2.copy(
                shadow = Shadow(
                    color = Color(0xFF00A693),
                    offset = Offset(0f, 0f),
                    blurRadius = 13f
                )
            ),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

    }
}