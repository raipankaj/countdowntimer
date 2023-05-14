package com.alltechies.timer.data

import androidx.annotation.Keep
import androidx.compose.runtime.Stable

@Keep
@Stable
data class Action(
    val duration: Long,
    val text: String = ""
)