package com.example.stepingprogressbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.stepingprogressbar.ui.navigation.BottomNavGraph
import com.example.stepingprogressbar.ui.progressbar.SteppingProgressBar
import com.example.stepingprogressbar.ui.theme.StepingProgressBarTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StepingProgressBarTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val currentScreenIndex = remember { mutableStateOf(0) }
                    val steppingItemsTitle =
                        remember { mutableStateListOf("1", "2", "3", "4", "5") }
                    val navController = rememberNavController()
                    val screensList = remember {
                        mutableListOf(
                            "First Screen",
                            "Second Screen",
                            "Third Screen",
                            "Forth Screen",
                            "Fifth Screen",
                            "Sixth Screen",
                            "Seventh Screen"
                        )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = 52.dp, top = 52.dp, start = 102.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {

                        BottomNavGraph(
                            navHostController = navController,
                            screensList = screensList
                        )

                        SteppingProgressBar(
                            itemsTitle = steppingItemsTitle,
                            currentScreenIndex = currentScreenIndex,
                            color = Color(0xFF00A693)
                        )

                        val coroutineScope = rememberCoroutineScope()

                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            // next
                            TextButton(
                                onClick = {
                                    coroutineScope.launch {
                                        screensList[if (currentScreenIndex.value < steppingItemsTitle.size - 1) currentScreenIndex.value++ else currentScreenIndex.value]
                                            .let {
                                                navController.navigate(screensList[currentScreenIndex.value])
                                            }
                                    }
                                }
                            ) {
                                Text(text = "next")
                            }

                            // previous
                            TextButton(
                                onClick = {
                                    coroutineScope.launch {
                                        screensList[if (currentScreenIndex.value > 0) currentScreenIndex.value-- else currentScreenIndex.value]
                                            .let {
                                                navController.navigate(screensList[currentScreenIndex.value])
                                            }
                                    }
                                }
                            ) {
                                Text(text = "previous")
                            }
                        }

                    }
                }
            }
        }
    }
}

