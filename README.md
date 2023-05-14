# Jetpack Compose Countdown Timer
A Jetpack Compose countdown timer library that allows you to easily add a customizable countdown timer to your Android app.

<h1>Features</h1>
<li>Change the color of the dialer background</li>
<li>Set the color of the progress indicator to match your app's theme</h1>
<li>Change the color of the dialer border</h1>
<li>Display the countdown time in the center of the dialer</h1>
<li>Display a label text just below the time</h1>

<h1>Installation</h1>
Add the following dependency to your app's build.gradle file:

```grradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

```gradle
dependencies {
    implementation 'com.github.raipankaj:countdowntimer:1.0.2'
}
```

<h1>Usage</h1>
To use the countdown timer in your app, simply add the CountDownTimer composable to your Jetpack Compose layout:

```kotlin
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
```

This creates a CountDownTimer instance with the following settings:

<li><b>actionList</b>: A list of Action objects, each representing a time interval and a label to display during that interval.</h1>
<li><b>dialerSize</b>: The size of the countdown timer dialer.</h1>
<li><b>dialerBackgroundColor</b>: The background color of the countdown timer dialer.</h1>
<li><b>dialerProgressColor</b>: The color of the progress indicator on the countdown timer dialer.</h1>
<li><b>dialerBorderColor</b>: The color of the border around the countdown timer dialer.</h1>
<li><b>onTimerExpired</b>: A lambda function to call when the countdown timer expires. This is where you can add any logic to handle the timer expiration event.</h1>

<h1>Example</h1>
Here's an example of how to use the countdown timer in a Jetpack Compose layout:

```kotlin
@Composable
fun ExerciseTimer() {
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
```

<h1>License</h1>
This library is licensed under the MIT License. See the LICENSE file for details.
