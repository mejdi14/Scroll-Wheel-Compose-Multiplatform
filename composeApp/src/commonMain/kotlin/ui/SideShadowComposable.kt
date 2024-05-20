package ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun LeftSideShadowLines(modifier: Modifier) {
    SideGradientBackground(
        modifier, Brush.horizontalGradient(
            colors = listOf(
                Color.Transparent,
                Color.White.copy(alpha = 1f)
            ),
            startX = Float.POSITIVE_INFINITY,
            endX = 0f
        )
    )

}

@Composable
fun RightSideShadowLines(modifier: Modifier) {
    SideGradientBackground(
        modifier, Brush.horizontalGradient(
            colors = listOf(
                Color.Transparent,
                Color.White.copy(alpha = 1f)
            ),
            startX = 0f,
            endX = Float.POSITIVE_INFINITY
        )
    )
}