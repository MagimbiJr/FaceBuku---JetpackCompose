package com.tana.facebookclone.presentation.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.tana.facebookclone.utils.toUiString
import com.tana.facebookclone.R
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.DatePickerDefaults
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun  DatePicker(
    value: LocalDate,
    onDateChange: (LocalDate) -> Unit,
    modifier: Modifier = Modifier,
    borderColor: Color = MaterialTheme.colors.onSurface.copy(ContentAlpha.medium),
    textColor: Color = MaterialTheme.colors.onSurface.copy(ContentAlpha.medium),
    tintColor: Color = MaterialTheme.colors.onSurface.copy(ContentAlpha.medium)
) {
    val dialogState = rememberMaterialDialogState()
    val colors = DatePickerDefaults.colors(
        headerTextColor = if (isSystemInDarkTheme()) Color.White else MaterialTheme.colors.onPrimary,
        calendarHeaderTextColor = MaterialTheme.colors.onBackground,
        dateActiveTextColor = if (isSystemInDarkTheme()) Color.White else MaterialTheme.colors.onPrimary
    )

    MaterialDialog(
        dialogState = dialogState,
        buttons = {
            positiveButton("OK")
            negativeButton("Cancel")
        }
    ) {
        datepicker(
            title = "Pick a date",
            colors = colors
        ) { date ->
            onDateChange(date)
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .border(
                width = .5.dp,
                color = borderColor,
                shape = RoundedCornerShape(8.dp)
            )
            .clip(RoundedCornerShape(8.dp))
            .clickable {
                dialogState.show()
            },
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = value.toUiString(),
                color = textColor
            )
            Icon(
                painter = painterResource(id = R.drawable.calender_icon),
                contentDescription = "Calender icon",
                modifier = modifier
                    .size(24.dp),
                tint = tintColor
            )
        }
    }
}