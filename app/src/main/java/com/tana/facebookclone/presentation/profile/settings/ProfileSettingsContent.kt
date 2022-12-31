package com.tana.facebookclone.presentation.profile.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tana.facebookclone.presentation.registration.components.FBCSecondaryButton

@Composable
fun ProfileSettingsContent(
    onClick: (String) -> Unit,
    onCopyLinkClicked: () -> Unit,
    modifier: Modifier
) {
    Column() {
        Spacer(modifier = modifier.height(12.dp))
        SettingList(
            onClick = onClick,
            onCopyLinkClicked = onCopyLinkClicked,
            modifier = modifier
        )
    }
}


@Composable
fun SettingList(
    onClick: (String) -> Unit,
    onCopyLinkClicked: () -> Unit,
    modifier: Modifier
) {
    LazyColumn() {
        items(items) { item ->
            SettingItem(
                name = item.second,
                icon = item.first,
                onClick = { onClick(item.second) },
                modifier = modifier
            )
        }
        item {
            Spacer(modifier = modifier.height(12.dp))
            UserProfileLinkItem(onCopyLinkClicked = onCopyLinkClicked, modifier = modifier)
        }
    }
}


@Composable
fun SettingItem(
    name: String,
    icon: Int,
    onClick: () -> Unit,
    modifier: Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(0.dp),
        backgroundColor = MaterialTheme.colors.surface.copy(.7f)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "Item icon",
                modifier = modifier
                    .size(24.dp)
            )
            Text(text = name)
        }
        Divider(modifier = modifier.fillMaxWidth())
    }
}

@Composable
fun UserProfileLinkItem(
    onCopyLinkClicked: () -> Unit,
    modifier: Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(0.dp),
        backgroundColor = MaterialTheme.colors.surface.copy(.7f)
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Text(text = "Your profile link", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = modifier.height(4.dp))
            Text(
                text = "Your personalized link on Facebook",
                fontSize = 16.sp,
                color = MaterialTheme.colors.onSurface.copy(.5f)
            )
            Spacer(modifier = modifier.height(12.dp))
            Divider()
            Spacer(modifier = modifier.height(12.dp))
            Text(
                text = "Url to user's profile here",
                fontSize = 16.sp,
                fontWeight = FontWeight.W500
            )
            Spacer(modifier = modifier.height(4.dp))
            FBCSecondaryButton(
                text = "Copy link",
                onClick = onCopyLinkClicked,
                modifier = modifier
                    .fillMaxWidth(),
                backgroundColor = MaterialTheme.colors.surface
            )
        }
    }
}