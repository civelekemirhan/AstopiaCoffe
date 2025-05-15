package com.example.astopiacoffe.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)



val ColorScheme.appBackground: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF363636) else Color.White

val ColorScheme.reverseTextColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color.White else Color.Black

val ColorScheme.appButtonColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF5D4037) else Color(0xFF5D4037)

val ColorScheme.appSelectedIndicatorColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF795548) else Color(0xFF795548)
val ColorScheme.appUnSelectedIndicatorColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFFD9D9D9) else Color(0xFFD9D9D9)
