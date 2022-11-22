package com.example.stepingprogressbar.ui.animation

import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun WizardFirstLaunchScreenAnim(
    duration: Int, delay: Int,
    content: @Composable () -> Unit
) {

    val state = remember {
        MutableTransitionState(false).apply {
            // Start the animation immediately.
            targetState = true // make it false to slide out
        }
    }

    AnimatedVisibility(visibleState = state,
        enter = slideInHorizontally(
            animationSpec = tween(
                durationMillis = duration,
                delayMillis = delay
            )
        ) { width -> width / 8 }
                + fadeIn(
            animationSpec = tween(durationMillis = duration, delayMillis = delay),
            // Fade in with the initial alpha of 0.3f.
            //initialAlpha = 0.3f
        ),
        exit = slideOutHorizontally(
            animationSpec = tween(
                durationMillis = duration,
                delayMillis = delay
            )
        ) { width -> -width / 8 }
                + fadeOut(animationSpec = tween(durationMillis = duration, delayMillis = delay))) {
        content()

    }

}
