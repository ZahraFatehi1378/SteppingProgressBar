package com.example.stepingprogressbar.ui.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = text)
    }
}