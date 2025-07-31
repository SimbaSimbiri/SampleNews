package com.example.samplenews.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val lightBlue      = Color(0xFFE3F2FD) // Light-blue 50
    val darkBlue       = Color(0xFF0D47A1) // Indigo-900
    val darkerBlue     = Color(0xFF001E3C) // Even deeper for dark-surface
    val white          = Color.White
    val black          = Color.Black
    val accentTeal     = Color(0xFF03DAC5)

    val colors = if (darkTheme) {
        darkColorScheme(
            primary      = darkerBlue,
            onPrimary    = white,

            background   = lightBlue,   // app window
            onBackground = black,       // text on window

            surface      = darkerBlue,    // card bg
            onSurface    = white,       // text on cards

            secondary    = accentTeal,
            onSecondary  = black         // if you ever use it
        )

    } else {
        lightColorScheme(

            primary      = lightBlue,
            onPrimary    = black,

            background   = white,  // app window
            onBackground = black,       // text on window

            surface      = lightBlue,    // card bg
            onSurface    = black,       // text on cards

            secondary    = accentTeal,
            onSecondary  = black
        )
    }
    val typography = Typography(
        bodyMedium = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            color =  colors.onBackground
        )
    )
    val shapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(4.dp),
        large = RoundedCornerShape(0.dp)
    )

    MaterialTheme(
        colorScheme = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}
