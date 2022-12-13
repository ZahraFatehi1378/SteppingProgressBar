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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
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
                    val isActivesList = remember { mutableStateListOf<Boolean>() }
                    isActivesList.clear()
                    isActivesList.addAll(
                        listOf(
                            true,
                            false,
                            false,
                            false,
                            false,
//                            false,
//                            false,
//                            false,
//                            false
                        )
                    )
                    val navController = rememberNavController()
                    val screensList = remember {
                        mutableListOf(
                            "zero",
                            "one",
                            "two",
                            "three",
                            "four",
//                            "five",
//                            "six",
//                            "seven",
//                            "eight"
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
                        )

                        SteppingProgressBar(isActivesList ,  Color(0xFF00A693))

                        val coroutineScope = rememberCoroutineScope()

                        val focusManager = LocalFocusManager.current
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            // next
                            TextButton(
                                onClick = {
                                    coroutineScope.launch {
                                        //  delay(2400)
                                        currentScreenIndex.value++
                                        isActivesList.set(currentScreenIndex.value, true)
                                        navController.navigate(screensList.get(currentScreenIndex.value))
                                    }
                                }
                            ) {
                                Text(text = "next")
                            }

                            // previous
                            TextButton(
                                onClick = {
                                    coroutineScope.launch {
                                        // delay(2400)
                                        isActivesList.set(currentScreenIndex.value, false)
                                        currentScreenIndex.value--
                                        navController.navigate(screensList.get(currentScreenIndex.value))
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

