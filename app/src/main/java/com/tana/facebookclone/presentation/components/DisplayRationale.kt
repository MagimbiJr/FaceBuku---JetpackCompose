package com.tana.facebookclone.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tana.facebookclone.R
import com.tana.facebookclone.presentation.registration.components.FBCPrimaryButton

@Composable
fun DisplayRationale(
    onGoToSetting: () -> Unit,
    modifier: Modifier
) {
    Surface(
        color = MaterialTheme.colors.background
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.gallery_icon),
                contentDescription = "Gallery icon",
                modifier = modifier
                    .size(45.dp),
                colorFilter = ColorFilter.tint(MaterialTheme.colors.onBackground)
            )
            Spacer(modifier = modifier.height(12.dp))
            Text(
                text = stringResource(id = R.string.rationale_title),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = modifier.height(12.dp))
            Text(
                text = stringResource(id = R.string.rationale_text), fontSize = 16.sp,
                color = MaterialTheme.colors.onBackground.copy(.6f),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = modifier.height(12.dp))
            FBCPrimaryButton(
                text = "Go to setting",
                onClick = onGoToSetting,
                modifier = modifier,
                enabled = true
            )
        }
    }
}