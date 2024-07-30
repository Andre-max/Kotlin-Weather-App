package com.octagontechnologies.sky_weather.ui.compose.theme

import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.GoogleFont
import com.octagontechnologies.sky_weather.R

@OptIn(ExperimentalTextApi::class)
val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val AdventProFont = GoogleFont("Advent Pro")
val AdventPro =
    FontFamily(
        androidx.compose.ui.text.googlefonts.Font(AdventProFont, provider, FontWeight.Normal),
        androidx.compose.ui.text.googlefonts.Font(AdventProFont, provider, FontWeight.Medium),
        androidx.compose.ui.text.googlefonts.Font(AdventProFont, provider, FontWeight.SemiBold),
        androidx.compose.ui.text.googlefonts.Font(AdventProFont, provider, FontWeight.Light),
    )

// We've created this fonts since Google Fonts isn't loaded in the Android Studio Preview and
// we use these fonts 99.9% of the time
val Poppins = FontFamily(
    Font(R.font.poppins_thin, FontWeight.Thin),
    Font(R.font.poppins_light, FontWeight.Light),
    Font(R.font.poppins_medium, FontWeight.Medium),
    Font(R.font.poppins_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.poppins_regular, FontWeight.Normal),
    Font(R.font.poppins_semibold, FontWeight.SemiBold),
    Font(R.font.poppins_bold, FontWeight.Bold)
)

val Cabin = FontFamily(
    Font(R.font.cabin_regular, FontWeight.Normal),
    Font(R.font.cabin_medium_ttf, FontWeight.Medium),
    Font(R.font.cabin_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.cabin_semibold, FontWeight.SemiBold),
    Font(R.font.cabin_bold, FontWeight.Bold)
)

val CabinSemiCondensed = FontFamily(
    Font(R.font.cabin_condensed_regular, FontWeight.Normal),
    Font(R.font.cabin_condensed_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.cabin_condensed_medium, FontWeight.Medium),
    Font(R.font.cabin_condensed_semibold, FontWeight.SemiBold),
    Font(R.font.cabin_condensed_bold, FontWeight.Bold)
)

val CabinCondensed = FontFamily(
    Font(R.font.cabin_condensed_regular, FontWeight.Normal),
    Font(R.font.cabin_condensed_medium, FontWeight.Medium),
    Font(R.font.cabin_condensed_semibold, FontWeight.SemiBold),
    Font(R.font.cabin_condensed_bold, FontWeight.Bold),
    Font(R.font.cabin_condensed_italic, FontWeight.Normal, FontStyle.Italic),
)

val QuickSand = FontFamily(
    Font(R.font.quicksand_light, FontWeight.Light),
    Font(R.font.quicksand_regular, FontWeight.Normal),
    Font(R.font.quicksand_medium, FontWeight.Medium),
    Font(R.font.quicksand_semibold, FontWeight.SemiBold),
    Font(R.font.quicksand_bold, FontWeight.Bold)
)

val Roboto = FontFamily(
    Font(R.font.roboto_light, FontWeight.Light),
    Font(R.font.roboto_regular, FontWeight.Normal),
    Font(R.font.roboto_medium_ttf, FontWeight.Medium),
    Font(R.font.roboto_bold_ttf, FontWeight.SemiBold),
    Font(R.font.roboto_black, FontWeight.Bold)
)