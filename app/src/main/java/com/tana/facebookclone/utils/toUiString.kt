package com.tana.facebookclone.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDate.toUiString(): String {
    val formatter = DateTimeFormatter.ofPattern("dd " +"MMMM".take(3) +" yyyy")
    return formatter.format(this)
}