import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.PI

@OptIn(ExperimentalResourceApi::class)
@Composable
fun WheelLikeImageSlider(images: List<DrawableResource>) {
    val radius = remember { 800f }
    val itemWidth = 150.dp
    val itemHeight = 250.dp

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(itemHeight)
            .pointerInput(Unit) {
                detectHorizontalDragGestures { change, dragAmount ->
                    change.consume()
                }
            }
    ) {
        items(images.size) { index ->
            Box(
                modifier = Modifier
                    .graphicsLayer {
                        val itemCenter = 300 / 2 + index * itemWidth.toPx()
                        val scrollCenter = 300 / 2
                        val centerDelta = itemCenter - scrollCenter
                        val angle = (centerDelta / radius) * PI.toFloat()
                        val scale = lerp(0.7f, 1f, 1 - abs(angle) / (PI.toFloat() / 2))

                        rotationY = -angle * (180f / PI.toFloat())
                        scaleX = scale
                        scaleY = scale
                        alpha = min(1f, 1 - abs(angle) / (PI.toFloat() / 2))
                    }
                    .size(itemWidth, itemHeight)
            ) {
                Image(
                    painter = painterResource(images[index]),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}