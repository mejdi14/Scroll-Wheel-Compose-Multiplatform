import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalViewConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import scrollwheeldemo.composeapp.generated.resources.Res
import scrollwheeldemo.composeapp.generated.resources.compose_multiplatform
import scrollwheeldemo.composeapp.generated.resources.image_1
import scrollwheeldemo.composeapp.generated.resources.image_2

@OptIn(ExperimentalResourceApi::class)
@Composable
@Preview
fun App() {
    MaterialTheme {
        val listImages = listOf(
            Res.drawable.image_1, Res.drawable.image_2,
            Res.drawable.image_1, Res.drawable.image_2,
            Res.drawable.image_1, Res.drawable.image_2,
            Res.drawable.image_1, Res.drawable.image_2,
            Res.drawable.image_1, Res.drawable.image_2,
            Res.drawable.image_1, Res.drawable.image_2,
            Res.drawable.image_1, Res.drawable.image_2,
            Res.drawable.image_1, Res.drawable.image_2,
            Res.drawable.image_1, Res.drawable.image_2,
            Res.drawable.image_1, Res.drawable.image_2,
            Res.drawable.image_1, Res.drawable.image_2,
            Res.drawable.image_1, Res.drawable.image_2,
            Res.drawable.image_1,
            Res.drawable.image_2,
            Res.drawable.image_1,
            Res.drawable.image_2,
        )
        val listState = rememberLazyListState()
        BoxWithConstraints {
            BottomGradientLayer(Modifier.align(Alignment.BottomCenter))
            LazyRow(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                state = listState
            ) {
                itemsIndexed(listImages) { index, it ->
                    val maxWidthDp: Dp = maxWidth
                    val compositeState = remember {
                        derivedStateOf {
                            Pair(
                                listState.firstVisibleItemIndex,
                                listState.firstVisibleItemScrollOffset
                            )
                        }
                    }
                    val halfScreenSize = (maxWidthDp / 2)

                    var visibleItemsCount = (listState.layoutInfo.visibleItemsInfo.size / 2)
                    val rotation =
                        dpToPx(halfScreenSize / 27) - (10f * (index - compositeState.value.first) - (compositeState.value.second / 25f))
                    val hot =
                    dpToPx(halfScreenSize / 30) - (1f * (index - compositeState.value.first) - (compositeState.value.second ))
                    println("rotation : $rotation")

                    Box(
                        modifier = Modifier
                            .size(70.dp)
                            .graphicsLayer {
                                rotationY = -(rotation * 1.5f)
                                scaleX = if (index == 0 || index  == 4) 0.9f else 1f
                                scaleY = if (index == 0 || index  == 4) 0.8f else 1f

                            }

                    ) {
                        Image(
                            painter = painterResource(it),
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.clip(shape = RoundedCornerShape(12.dp))
                        )
                    }
                    Spacer(Modifier.width(5.dp))
                }
            }
        }
    }

}

@Composable
fun BottomGradientLayer(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(90.dp)
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color.Transparent,
                        Color.White.copy(alpha = 1f)
                    ),
                    startX = 0f,
                    endX = Float.POSITIVE_INFINITY
                )
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {}
}