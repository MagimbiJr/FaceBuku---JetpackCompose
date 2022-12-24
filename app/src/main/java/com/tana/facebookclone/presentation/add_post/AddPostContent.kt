package com.tana.facebookclone.presentation.add_post

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.tana.facebookclone.R
import com.tana.facebookclone.domain.modal.User
import com.tana.facebookclone.presentation.components.FBCBasicTextField
import com.tana.facebookclone.presentation.components.FBCDialog
import com.tana.facebookclone.presentation.components.FBCTopAppBar
import com.tana.facebookclone.presentation.registration.components.FBCPrimaryButton

@Composable
fun AddPostContent(
    uiState: AddPostUiState,
    scrollState: ScrollState,
    onPostClick: () -> Unit,
    onNavigationIconClick: () -> Unit,
    onCaptionChange: (String) -> Unit,
    modifier: Modifier,
    viewModel: AddPostViewModel,
) {
    var isUploadingFailed = remember { mutableStateOf(viewModel.isUploadingFailed) }

    if (isUploadingFailed.value) {
        FBCDialog(
            title = "Uploading Failed",
            text = uiState.errorMessage!!,
            onDismissRequest = {
                isUploadingFailed.value = false
            },
            modifier = modifier
        )
    }

    Scaffold(
        topBar = {
            FBCTopAppBar(
                title = "Create post",
                navigationIcon = R.drawable.back_arrow_icon,
                onNavigationIconClick = onNavigationIconClick,
                modifier = modifier,
                iconSize = 24.dp,
                actions = {
                    FBCPrimaryButton(
                        text = "POST",
                        onClick = onPostClick,
                        modifier = modifier,
                        enabled = true
                    )
                }
            )
        }
    ) {
        Column(
            modifier = modifier
                .padding(it)
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            uiState.user?.let { user ->
                AddPostUserInfoRow(
                    uiState = uiState,
                    modifier = modifier,
                    onInstaShareChipClick = {},
                    onAlbumChipClick = {},
                    onAudienceChipClick = {},
                    user = user
                )
            }
            Spacer(modifier = modifier.height(12.dp))
            FBCBasicTextField(
                value = uiState.caption,
                onValueChange = onCaptionChange,
                placeHolder = stringResource(id = R.string.add_post_textfield_label)
            )
            Spacer(modifier = modifier.height(12.dp))
            Box() {
                AsyncImage(
                    model = uiState.uri?.toString(),
                    contentDescription = "Image to post",
                    modifier = modifier
                        .fillMaxWidth()
                )
            }
            Spacer(modifier = modifier.height(24.dp))
            Row(
                modifier = modifier
                    .clickable { viewModel.onAddMoreClick() }
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.gallery_icon),
                    contentDescription = "Add Image",
                    modifier = modifier
                        .size(24.dp),
                    tint = MaterialTheme.colors.primary
                )
                Spacer(modifier = modifier.width(12.dp))
                Text(
                    text = "Add more",
                    color = MaterialTheme.colors.primary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
            Spacer(modifier = modifier.height(32.dp))
        }
    }
}

@Composable
@OptIn(ExperimentalMaterialApi::class)
private fun AddPostUserInfoRow(
    uiState: AddPostUiState,
    user: User,
    modifier: Modifier,
    onAudienceChipClick: () -> Unit,
    onAlbumChipClick: () -> Unit,
    onInstaShareChipClick: () -> Unit,
    //imageData: MutableState<Uri?>
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .padding(top = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val chipColors = ChipDefaults.chipColors(
            backgroundColor = MaterialTheme.colors.background,
            contentColor = MaterialTheme.colors.onBackground
        )
        AsyncImage(
            model = ImageRequest.Builder(
                context = LocalContext.current
            )
                .data(user.userProfilePic)
                .placeholder(R.drawable.person_icon)
                .build(),
            contentDescription = "User dp",
            modifier = modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colors.surface),
            contentScale = ContentScale.Crop
        )
        Column {
            Text(
                text = "${user.firstName} ${user.lastName}",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            ChipsRow(
                chipColors = chipColors,
                onAudienceChipClick = onAudienceChipClick,
                onAlbumChipClick = onAlbumChipClick,
                onInstaShareChipClick = onInstaShareChipClick,
                modifier = modifier
            )
        }
    }
}

@Composable
@OptIn(ExperimentalMaterialApi::class)
private fun ChipsRow(
    chipColors: ChipColors,
    onAudienceChipClick: () -> Unit,
    onAlbumChipClick: () -> Unit,
    onInstaShareChipClick: () -> Unit,
    modifier: Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Chip(
            onClick = onAudienceChipClick,
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(
                width = .3.dp,
                color = MaterialTheme.colors.onBackground.copy(.6f)
            ),
            colors = chipColors,
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.world_map_icon),
                    contentDescription = "",
                    modifier = modifier
                        .size(10.dp),
                    tint = MaterialTheme.colors.onBackground.copy(.6f)
                )
                Text(
                    text = "Public",
                    fontSize = 12.sp,
                    color = MaterialTheme.colors.onBackground.copy(.6f)
                )
                Icon(
                    painter = painterResource(id = R.drawable.dropdown_icon),
                    contentDescription = "",
                    modifier = modifier
                        .size(10.dp),
                    tint = MaterialTheme.colors.onBackground.copy(.6f)
                )
            }
        }
        Chip(
            onClick = onAlbumChipClick,
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(
                width = .3.dp,
                color = MaterialTheme.colors.onBackground.copy(.6f)
            ),
            colors = chipColors,
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.plus_icon),
                    contentDescription = "",
                    modifier = modifier
                        .size(8.dp),
                    tint = MaterialTheme.colors.onBackground.copy(.6f)
                )
                Text(
                    text = "Album",
                    fontSize = 12.sp,
                    color = MaterialTheme.colors.onBackground.copy(.6f)
                )
                Icon(
                    painter = painterResource(id = R.drawable.dropdown_icon),
                    contentDescription = "",
                    modifier = modifier
                        .size(10.dp),
                    tint = MaterialTheme.colors.onBackground.copy(.6f)
                )
            }
        }
        Chip(
            onClick = onInstaShareChipClick,
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(
                width = .3.dp,
                color = MaterialTheme.colors.onBackground.copy(.6f)
            ),
            colors = chipColors,
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.instagram_b_w_icon),
                    contentDescription = "",
                    modifier = modifier
                        .size(10.dp),
                    tint = MaterialTheme.colors.onBackground.copy(.6f)
                )
                Text(
                    text = "Off",
                    fontSize = 12.sp,
                    color = MaterialTheme.colors.onBackground.copy(.6f)
                )
                Icon(
                    painter = painterResource(id = R.drawable.dropdown_icon),
                    contentDescription = "",
                    modifier = modifier
                        .size(10.dp),
                    tint = MaterialTheme.colors.onBackground.copy(.6f)
                )
            }
        }
    }
}