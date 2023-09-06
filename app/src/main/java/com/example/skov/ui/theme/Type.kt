package com.example.skov.ui.theme

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import com.example.skov.R


private val fontProvider = GoogleFont.Provider(
        providerAuthority = "com.google.android.gms.fonts",
        providerPackage = "com.google.android.gms",
        certificates = R.array.com_google_android_gms_fonts_certs
)

private val googleFontQuicksand = GoogleFont("Quicksand")
private val googleFontDancing = GoogleFont("Dancing Script")

val fontFamilyQuicksand = FontFamily(
        Font(
                googleFont = googleFontQuicksand,
                fontProvider = fontProvider
        )
)

val fontFamilyDance = FontFamily(
        Font(
                googleFont = googleFontDancing,
                fontProvider = fontProvider,
                weight = FontWeight.Normal
        )
)

// Set of Material typography styles to start with
//val Typography = Typography(
//
//        displayLarge = typography.displayLarge.copy(fontFamily = fontFamily),
//        displayMedium = typography.displayMedium.copy(fontFamily = fontFamily),
//        displaySmall = typography.displaySmall.copy(fontFamily = fontFamily),
//
//        headlineLarge = typography.headlineLarge.copy(fontFamily = fontFamily),
//        headlineMedium = typography.headlineMedium.copy(fontFamily = fontFamily),
//        headlineSmall = typography.headlineSmall.copy(fontFamily = fontFamily),
//
//        titleLarge = typography.titleLarge.copy(fontFamily = fontFamily),
//        titleMedium = typography.titleMedium.copy(fontFamily = fontFamily),
//        titleSmall = typography.titleSmall.copy(fontFamily = fontFamily),
//
//        bodyLarge = typography.bodyLarge.copy(fontFamily = fontFamily),
//        bodyMedium = typography.bodyMedium.copy(fontFamily = fontFamily),
//        bodySmall = typography.bodySmall.copy(fontFamily = fontFamily),
//
//        labelLarge = typography.labelLarge.copy(fontFamily = fontFamily),
//        labelMedium = typography.labelMedium.copy(fontFamily = fontFamily),
////        labelSmall = typography.labelSmall.copy(fontFamily = fontFamily),
//
//        labelSmall = typography.labelSmall.copy(fontFamily = fontFamily)
//
//)

