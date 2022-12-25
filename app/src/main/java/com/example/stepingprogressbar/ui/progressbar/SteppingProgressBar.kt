package com.example.stepingprogressbar.ui.progressbar

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.stepingprogressbar.ui.animation.WizardFirstLaunchScreenAnim
import kotlinx.coroutines.launch


@Composable
fun SteppingProgressBar(
    itemsTitle: SnapshotStateList<String>,
    color: Color,
    currentScreenIndex: MutableState<Int>,
) {

    val ltr = LocalLayoutDirection.current == LayoutDirection.Ltr
    val progressingPercent = remember { Animatable(0f) }
    var enabled by remember { mutableStateOf(false) }
    val alpha: Float by animateFloatAsState(
        targetValue = (currentScreenIndex.value + 0.5f),
        animationSpec = tween(durationMillis = 800)
    )

    Box(
        modifier = Modifier
            .padding(bottom = 12.dp, end = 102.dp)
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

        LaunchedEffect(currentScreenIndex.value) {
            enabled = !enabled
            println(alpha)
        }

        Box(
            modifier = Modifier
                .padding(horizontal = 36.dp)
                .fillMaxWidth()
                .height(6.dp)
                .alpha(alpha)
                .drawBehind {

                    drawRect(
                        color = Color(0xFFFEFFFF),
                        size = size
                    )

                    drawRect(
                        color = Color.LightGray,
                        topLeft = Offset(
                            x = if (ltr) 0f else size.width,
                            y = 0F
                        ),
                        size = Size(
                            width = (if (ltr) 1 else -1) * size.width * progressingPercent.value / 100,
                            height = size.height
                        )
                    )

                    drawRect(
                        color = color,
                        topLeft = Offset(
                            x = if (ltr) 0f else size.width,
                            y = 0F
                        ),
                        size = Size(
                            width = (if (ltr) 1 else -1) * (alpha * size.width / (itemsTitle.size - 1)) * progressingPercent.value / 100,
                            height = size.height
                        )
                    )
                })
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            itemsTitle.forEachIndexed { index: Int, itemTitle: String ->
                WizardFirstLaunchScreenAnim(duration = 1500, delay = 900 + index * 100) {
                    CircularBottomItem(
                        text = itemTitle,
                        color = if (index <= currentScreenIndex.value) color else Color.LightGray
                    )
                }
            }

        }

    }
}