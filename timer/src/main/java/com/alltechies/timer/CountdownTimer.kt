package com.alltechies.timer


import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alltechies.timer.data.Action
import kotlinx.coroutines.delay

@OptIn(ExperimentalTextApi::class)
@Composable
fun CountDownTimer(
    actionList: List<Action>,
    dialerSize: Dp = 240.dp,
    dialerBackgroundColor: Color = Color(0xFF203F59),
    dialerBorderColor: Color = Color(0xFF223740),
    dialerProgressColor: Color = Color(0xFFBF4B4B),
    timerTextColor: Color = Color.White,
    timerLabelColor: Color = Color.White,
    onTimerExpired: () -> Unit
) {

    val animatedProgress = remember { Animatable(initialValue = 0.0f) }
    val textMeasure = rememberTextMeasurer()

    var currentIndex by remember {
        mutableStateOf(0)
    }

    var progress by remember(currentIndex) {
        mutableStateOf(actionList[currentIndex].duration)
    }

    LaunchedEffect(key1 = currentIndex) {
        while (progress > 0) {
            delay(1000)
            progress -= 1000
        }

        if (currentIndex + 1 < actionList.size) {
            currentIndex++
        } else {
            onTimerExpired()
        }
    }

    LaunchedEffect(key1 = currentIndex) {
        if (currentIndex < actionList.size) {
            animatedProgress.snapTo(0.0f)
        }
        animatedProgress.animateTo(
            targetValue = 1.0f, animationSpec = tween(
                durationMillis = actionList[currentIndex].duration.toInt(), easing = LinearEasing
            )
        )
    }

    Timer(
        dialerSize = dialerSize,
        dialerBackgroundColor = dialerBackgroundColor,
        dialerBorderColor = dialerBorderColor,
        dialerProgressColor = dialerProgressColor,
        progress = animatedProgress.value,
        timerProgress = progress,
        action = actionList[currentIndex],
        textMeasurer = textMeasure,
        timerTextColor = timerTextColor,
        timerLabelColor = timerLabelColor
    )
}

@OptIn(ExperimentalTextApi::class)
@Composable
fun Timer(
    dialerSize: Dp,
    dialerBackgroundColor: Color,
    dialerBorderColor: Color,
    dialerProgressColor: Color,
    progress: Float,
    timerProgress: Long,
    action: Action,
    textMeasurer: TextMeasurer,
    timerTextColor: Color = Color.White,
    timerLabelColor: Color = Color.White
) {

    Canvas(
        modifier = Modifier
            .size(dialerSize)
            .clip(CircleShape)
            .background(dialerBackgroundColor)
    ) {
        val canvasSize = size.minDimension
        val radius = canvasSize / 2 - 16.dp.toPx() / 2

        drawCircle(
            color = dialerBorderColor,
            radius = radius,
            center = size.center,
            style = Stroke(width = 16.dp.toPx())
        )

        drawArc(
            color = dialerProgressColor,
            startAngle = -90f,
            sweepAngle = (progress / 1.0f) * 360f,
            useCenter = false,
            topLeft = size.center - Offset(radius, radius),
            size = Size(radius * 2, radius * 2),
            style = Stroke(
                width = 8.dp.toPx(),
                cap = StrokeCap.Round
            )
        )

        val time = formatTime(timerProgress)
        val title = action.text

        drawText(
            textMeasurer = textMeasurer, text = time,
            style = TextStyle(
                fontSize = 46.sp,
                color = timerTextColor
            ),
            topLeft = size.center - Offset(
                textMeasurer.measure(time).size.width.toFloat() + 16.dp.toPx(),
                textMeasurer.measure(time).size.height.toFloat() + 16.dp.toPx()
            )
        )

        val titleSize = textMeasurer.measure(title)
        val timeSize = textMeasurer.measure(time)

        val centerOffset = Offset(
            size.width / 2 - titleSize.size.width / 2,
            size.height / 2 - (titleSize.size.height + timeSize.size.height - 86.dp.toPx()) / 2
        )

        drawText(
            textMeasurer = textMeasurer, text = title,
            style = TextStyle(
                fontSize = 18.sp,
                color = timerLabelColor,
                textAlign = TextAlign.Center
            ),
            topLeft = centerOffset
        )
    }
}

private fun formatTime(timeInMillis: Long): String {
    val minutes = (timeInMillis / 1000) / 60
    val seconds = (timeInMillis / 1000) % 60
    return "%02d:%02d".format(minutes, seconds)
}