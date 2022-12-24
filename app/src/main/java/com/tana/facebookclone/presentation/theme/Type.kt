package com.tana.facebookclone.presentation.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.tana.facebookclone.R

private val MontserratBold = FontFamily(Font(R.font.montserrat_bold))
private val MontserratMedium = FontFamily(Font(R.font.montserrat_medium))
private val MontserratRegular = FontFamily(Font(R.font.montserrat_regular))
private val MontserratLight = FontFamily(Font(R.font.montserrat_light))
// Set of Material typography styles to start with
val Typography = Typography(
    h1 = TextStyle(
        fontFamily = MontserratRegular,
        fontWeight = FontWeight.W400,
        fontSize = 65.sp
    ),
    h2 = TextStyle(
        fontFamily = MontserratRegular,
        fontWeight = FontWeight.W400,
        fontSize = 55.sp
    ),
    h3 = TextStyle(
        fontFamily = MontserratRegular,
        fontWeight = FontWeight.W400,
        fontSize = 45.sp
    ),
    h4 = TextStyle(
        fontFamily = MontserratRegular,
        fontWeight = FontWeight.W400,
        fontSize = 35.sp
    ),
    h5 = TextStyle(
        fontFamily = MontserratMedium,
        fontWeight = FontWeight.W300,
        fontSize = 30.sp
    ),
    h6 = TextStyle(
        fontFamily = MontserratMedium,
        fontWeight = FontWeight.W300,
        fontSize = 25.sp
    ),
    body1 = TextStyle(
        fontFamily = MontserratMedium,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    button = TextStyle(
        fontFamily = MontserratBold,
        fontSize = 13.sp
    ),
    caption = TextStyle(
        fontFamily = MontserratRegular,
        fontSize = 12.sp
    ),
    overline = TextStyle(
        fontFamily = MontserratMedium,
        fontSize = 10.sp,
        letterSpacing = 1.5.sp
    ),
    body2 = TextStyle(
        fontFamily = MontserratMedium,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        letterSpacing = 0.25.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = MontserratRegular,
        fontSize = 16.sp,
        letterSpacing = 0.15.sp
    ),
)