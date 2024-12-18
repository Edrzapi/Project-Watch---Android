package uk.co.devfoundry.projectwatch.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

// Define colors that reflect an iOS-like aesthetic
private val iOSLightBlue = Color(0xFF007AFF) // Default iOS blue
private val iOSSecondary = Color(0xFF5856D6) // Light purple accent
private val iOSTertiary = Color(0xFF34C759) // Green for tertiary accents

private val iOSDarkBlue = Color(0xFF0A84FF) // Dark mode iOS blue
private val iOSDarkSecondary = Color(0xFF5E5CE6) // Dark purple accent
private val iOSDarkTertiary = Color(0xFF30D158) // Dark green

private val DarkColorScheme = darkColorScheme(
    primary = iOSDarkBlue,
    secondary = iOSDarkSecondary,
    tertiary = iOSDarkTertiary,
    background = Color(0xFF1C1C1E), // Dark background
    surface = Color(0xFF2C2C2E), // Elevated surface color
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFFE5E5EA),
    onSurface = Color(0xFFE5E5EA)
)

private val LightColorScheme = lightColorScheme(
    primary = iOSLightBlue,
    secondary = iOSSecondary,
    tertiary = iOSTertiary,
    background = Color(0xFFF2F2F7), // Light background
    surface = Color(0xFFFFFFFF), // Elevated surface color
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1C1E),
    onSurface = Color(0xFF1C1C1E)
)

@Composable
fun ProjectWatchTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography, // Customize Typography if needed for iOS-like fonts
        content = content
    )
}
