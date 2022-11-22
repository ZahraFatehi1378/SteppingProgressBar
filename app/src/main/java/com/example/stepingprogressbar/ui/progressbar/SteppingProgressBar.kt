package com.example.stepingprogressbar.ui.progressbar

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.stepingprogressbar.ui.animation.WizardFirstLaunchScreenAnim
import kotlinx.coroutines.launch


@Composable
fun SteppingProgressBar(isActivesList: SnapshotStateList<Boolean>) {

    val progressingPercent = remember {
        Animatable(0f)
    }

    Box(
        modifier = Modifier.padding(bottom = 12.dp , end = 102.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {

        LaunchedEffect(progressingPercent) {
            launch {
                progressingPercent.animateTo(
                    100f,
                    animationSpec = tween(durationMillis = 2000, delayMillis = 900)
                )
            }
        }


        Box( modifier = Modifier
            .padding(horizontal = 36.dp).fillMaxWidth().height(6.dp)
            .drawBehind {

                drawRect(
                    color = Color(0xFFFEFFFF),
                    size= size
                )

                drawRect(
                    color = Color.LightGray,
                    topLeft = Offset(
                        x = 0f,
                        y = 0F
                    ),
                    size = Size(
                        width = size.width * progressingPercent.value / 100,
                        height = size.height
                    )
                )

                drawRect(
                    color = Color(0xFF00A693),
                    topLeft = Offset(
                        x = 0f,
                        y = 0F
                    ),
                    size = Size(
                        width = ((isActivesList.count { it } - 0.5f) * size.width / (isActivesList.size - 1)) * progressingPercent.value / 100,
                        height = size.height
                    )
                )
            })
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            isActivesList.forEachIndexed { index, isActive ->
                WizardFirstLaunchScreenAnim(duration = 1500, delay = 900 + index * 100) {

                    CircularBottomItem(
                        text = index.toString(),
                        color = if (isActive) MaterialTheme.colors.primary else Color.LightGray
                    )
                }
            }

        }

    }

}