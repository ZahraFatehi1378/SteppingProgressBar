package com.example.stepingprogressbar.ui.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun BottomNavGraph(
    navHostController: NavHostController,
) {

    NavHost(
        navController = navHostController,
        startDestination = "zero"
    ) {
        composable(
            route = "zero",
        ) {
            FakeScreen("zero")
        }
        composable(route = "one") {
            FakeScreen("one")
        }
        composable(route = "two") {
            FakeScreen(text = "two")
        }
        composable(route = "three") {
            FakeScreen(text = "three")
        }
        composable(route = "four") {
            FakeScreen(text = "four")
        }
        composable(route = "five") {
            FakeScreen(text = "five")
        }
        composable (route = "six") {
            FakeScreen(text = "six")
        }
        composable(route = "seven") {
            FakeScreen(text = "seven")
        }
        composable(route = "eight") {
            FakeScreen(text = "eight")
        }
    }
}


@Composable
fun FakeScreen(text: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = text)
    }
}