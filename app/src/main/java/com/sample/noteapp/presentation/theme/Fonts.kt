package com.sample.noteapp.presentation.theme


import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.sample.noteapp.R


val ProximaNovaAlt = FontFamily(
    Font(R.font.proxima_nova_alt_regular, FontWeight.Normal),
)
val NoeDisplay = FontFamily(
    Font(R.font.noe_display_bold, FontWeight.Normal),
    Font(R.font.noe_display_bold, FontWeight.Bold)
)
val AppTypography = Typography(
    bodyLarge = TextStyle(
        fontFamily = ProximaNovaAlt,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = ProximaNovaAlt,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = NoeDisplay,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = NoeDisplay,
        fontWeight = FontWeight.Medium,
        fontSize = 24.sp
    ),
    labelLarge = TextStyle(
        fontFamily = ProximaNovaAlt,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    )
)