package com.martini.complicated.details.ui.theme

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize

fun Modifier.shimmer(): Modifier = composed {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }

    val transition = rememberInfiniteTransition()
    val startOffsetX by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000
            )
        )
    )

    val colors = if (isSystemInDarkTheme()) {
        listOf(
            Color.DarkGray,
            Color.LightGray,
            Color.DarkGray
        )
    } else {
        listOf(
            Color.LightGray,
            Color.DarkGray,
            Color.LightGray
        )
    }

    background(
        brush = Brush.horizontalGradient(
            colors = colors,
            startX = startOffsetX,
            endX = startOffsetX + size.width.toFloat()
        )
    ).onGloballyPositioned {
        size = it.size
    }
}