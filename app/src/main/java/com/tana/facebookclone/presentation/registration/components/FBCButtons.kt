package com.tana.facebookclone.presentation.registration.components

import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tana.facebookclone.presentation.theme.buttonHeight

@Composable
fun FBCPrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    backgroundColor: Color = MaterialTheme.colors.primary
) {
    val colors = ButtonDefaults.buttonColors(
        backgroundColor = backgroundColor,
        contentColor = MaterialTheme.colors.onPrimary,
        disabledBackgroundColor = backgroundColor.copy(.35f)
    )
    Button(
        onClick = onClick,
        modifier = modifier
            .heightIn(buttonHeight),
        colors = colors,
        shape = MaterialTheme.shapes.medium,
        enabled = enabled,
        interactionSource = interactionSource
    ) {
        Text(
            text = text,
            modifier = modifier,
            textAlign = TextAlign.Center,
            fontSize = 14.sp
        )
    }
}

@Composable
fun FBCSecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier,
    icon: Int? = null,
    backgroundColor: Color = MaterialTheme.colors.secondary
) {
    val colors = ButtonDefaults.buttonColors(
        backgroundColor = backgroundColor
    )
    Button(
        onClick = onClick,
        modifier = modifier
            .heightIn(buttonHeight),
        colors = colors,
        shape = MaterialTheme.shapes.medium
    ) {
        if (icon != null) {
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = "Icon",
                    modifier = modifier
                        .size(14.dp)
                )
                Spacer(modifier = modifier.width(12.dp))

                Text(
                    text = text,
                    color = MaterialTheme.colors.onSurface,
                    textAlign = TextAlign.Center,
                )
            }
        } else {
            Text(
                text = text,
                color = MaterialTheme.colors.onSurface,
                modifier = modifier
                    .padding(horizontal = 12.dp),
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
fun FBCTextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier,
) {
    TextButton(
        onClick = onClick,
        modifier = modifier
            .heightIn(buttonHeight),
        shape = MaterialTheme.shapes.medium
    ) {
        Text(
            text = text,
            color = MaterialTheme.colors.primary,
            modifier = modifier
                .padding(horizontal = 12.dp),
            textAlign = TextAlign.Center,
            fontSize = 16.sp,

            )
    }
}