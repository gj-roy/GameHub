package ca.on.hojat.gamenews.core.common_ui.widgets.toolbars

import android.content.res.Configuration
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ca.on.hojat.gamenews.core.R
import ca.on.hojat.gamenews.core.common_ui.theme.GameNewsTheme

@Composable
fun Toolbar(
    title: String,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    backgroundColor: Color = GameNewsTheme.colors.primary,
    contentColor: Color = contentColorFor(backgroundColor),
    elevation: Dp = ToolbarElevation,
    titleTextStyle: TextStyle = GameNewsTheme.typography.h5,
    leftButtonIcon: Painter? = null,
    rightButtonIcon: Painter? = null,
    onLeftButtonClick: (() -> Unit)? = null,
    onRightButtonClick: (() -> Unit)? = null
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        color = backgroundColor,
        contentColor = contentColor,
        elevation = elevation,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(contentPadding)
                .height(ToolbarHeight),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            val titleLeftPadding = getTitleHorizontalPadding(leftButtonIcon)
            val titleRightPadding = getTitleHorizontalPadding(rightButtonIcon)

            if (leftButtonIcon != null) {
                Button(
                    icon = leftButtonIcon,
                    onClick = { onLeftButtonClick?.invoke() }
                )
            }

            Text(
                text = title,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = titleLeftPadding, end = titleRightPadding),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                style = titleTextStyle,
            )

            if (rightButtonIcon != null) {
                Button(
                    icon = rightButtonIcon,
                    onClick = { onRightButtonClick?.invoke() }
                )
            }
        }
    }
}

@Composable
private fun getTitleHorizontalPadding(icon: Painter?): Dp {
    return if (icon != null) {
        GameNewsTheme.spaces.spacing_4_0
    } else {
        GameNewsTheme.spaces.spacing_5_0
    }
}

@Composable
private fun Button(
    icon: Painter,
    onClick: () -> Unit,
) {
    IconButton(
        modifier = Modifier.size(ToolbarHeight),
        onClick = onClick,
    ) {
        Icon(
            painter = icon,
            contentDescription = null,
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ToolbarPreviewWithTitle() {
    GameNewsTheme {
        Toolbar(
            title = "Toolbar"
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ToolbarPreviewWithLongTitle() {
    GameNewsTheme {
        Toolbar(
            title = "Toolbar toolbar toolbar toolbar toolbar toolbar toolbar toolbar"
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ToolbarPreviewWithBothIcons() {
    GameNewsTheme {
        Toolbar(
            title = "Toolbar",
            leftButtonIcon = painterResource(R.drawable.arrow_left),
            rightButtonIcon = painterResource(R.drawable.magnify)
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ToolbarPreviewWithLeftIcon() {
    GameNewsTheme {
        Toolbar(
            title = "Toolbar",
            leftButtonIcon = painterResource(R.drawable.arrow_left),
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ToolbarPreviewWithRightIcon() {
    GameNewsTheme {
        Toolbar(
            title = "Toolbar",
            rightButtonIcon = painterResource(R.drawable.magnify)
        )
    }
}
