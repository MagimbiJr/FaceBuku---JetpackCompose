package com.tana.facebookclone.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tana.facebookclone.R
import com.tana.facebookclone.presentation.registration.components.FBCPrimaryButton
import com.tana.facebookclone.presentation.theme.FacebookCloneTheme

@Composable
fun NoStoragePermission(
    onGrantAccess: () -> Unit,
    modifier: Modifier
) {
    Surface(
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = modifier
                .padding(12.dp),
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
                text = stringResource(id = R.string.gallery_permission_title),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,

                )
            Spacer(modifier = modifier.height(12.dp))
            Text(
                text = stringResource(id = R.string.gallery_permission_text),
                fontSize = 16.sp,
                color = MaterialTheme.colors.onBackground.copy(.6f),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = modifier.height(16.dp))
            FBCPrimaryButton(
                text = "Allow access",
                onClick = onGrantAccess,
                modifier = modifier,
                enabled = true
            )
        }
    }
}

@Preview(name = "Night", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "Day", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PermissionPreview() {
    FacebookCloneTheme() {
        Surface() {
            NoStoragePermission(
                onGrantAccess = {},
                modifier = Modifier
            )
        }
    }
}