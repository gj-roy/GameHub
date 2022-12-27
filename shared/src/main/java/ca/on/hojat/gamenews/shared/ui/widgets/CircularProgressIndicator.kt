package ca.on.hojat.gamenews.shared.ui.widgets

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import ca.on.hojat.gamenews.core.common_ui.theme.GameNewsTheme

@Composable
fun GameNewsProgressIndicator(
    modifier: Modifier = Modifier,
    color: Color = GameNewsTheme.colors.secondary,
    strokeWidth: Dp = ProgressIndicatorDefaults.StrokeWidth,
) {
    CircularProgressIndicator(
        modifier = modifier,
        color = color,
        strokeWidth = strokeWidth,
    )
}
