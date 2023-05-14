package com.alltechies.countdown

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.alltechies.countdown.ui.theme.CountdownTheme
import com.alltechies.timer.CountDownTimer
import com.alltechies.timer.data.Action
import kotlin.time.Duration.Companion.seconds

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CountdownTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CountDownTimer(
                            actionList = listOf(
                                Action(45.seconds.inWholeMilliseconds, "Inhale"),
                                Action(30.seconds.inWholeMilliseconds, "Exhale"),
                                Action(45.seconds.inWholeMilliseconds, "Inhale"),
                                Action(30.seconds.inWholeMilliseconds, "Exhale"),
                                Action(45.seconds.inWholeMilliseconds, "Inhale"),
                                Action(30.seconds.inWholeMilliseconds, "Exhale"),
                            ),
                            dialerSize = 240.dp,
                            dialerBackgroundColor = MaterialTheme.colorScheme.primary,
                            dialerProgressColor = Color.White,
                            dialerBorderColor = MaterialTheme.colorScheme.tertiary,
                            onTimerExpired = {

                            }
                        )
                    }
                }
            }
        }
    }
}