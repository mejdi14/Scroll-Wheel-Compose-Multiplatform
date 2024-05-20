import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import co.touchlab.kermit.Logger
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import scrollwheeldemo.composeapp.generated.resources.Res
import scrollwheeldemo.composeapp.generated.resources.image_1
import scrollwheeldemo.composeapp.generated.resources.image_2
import kotlin.math.absoluteValue

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

            LazyRow(
                modifier = Modifier.fillMaxSize().background(color = Color.White),
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
                    val imageCenter = listState.firstVisibleItemIndex * (dpToPx(70.dp) + dpToPx(10.dp)) +
                            listState.firstVisibleItemScrollOffset +
                            index * (dpToPx(70.dp)  + dpToPx(10.dp)) +
                            dpToPx(70.dp)  / 2

                    // Compute scale based on the distance from the center of the screen
                    val distanceFromCenter = kotlin.math.abs(imageCenter - dpToPx(halfScreenSize))
                    val maxDistance = dpToPx(maxWidthDp) / 2
                    val scale = 1 - 0.2 * (distanceFromCenter / maxDistance).coerceIn(0f, 1f)

                    var visibleItemsCount = (listState.layoutInfo.visibleItemsInfo.size / 2)
                    val rotation =
                        dpToPx(halfScreenSize / 27) - (10f * (index - compositeState.value.first) - (compositeState.value.second / 25f))
                    val hot =
                    dpToPx(halfScreenSize / 200) - ( ((index - compositeState.value.first) * (compositeState.value.second )) / 200)

                    if(index == 4){

                    Logger.i("hot1 ${rotation}")
                    Logger.i("hot2 ${(1f -(rotation / 100) )}")
                    }

                    Box(
                        modifier = Modifier
                            .size(70.dp)
                            .graphicsLayer {
                                rotationY = -(rotation * 1.6f)
                                scaleX = (1f -((rotation.absoluteValue ) / 200) )
                                //scaleY = 1f - hot


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
            BottomGradientLayer(Modifier.align(Alignment.CenterStart), Brush.horizontalGradient(
                colors = listOf(
                    Color.Transparent,
                    Color.White.copy(alpha = 1f)
                ),
                startX = Float.POSITIVE_INFINITY,
                endX = 0f
            ))
            BottomGradientLayer(
                Modifier.align(Alignment.CenterEnd), Brush.horizontalGradient(
                    colors = listOf(
                        Color.Transparent,
                        Color.White.copy(alpha = 1f)
                    ),
                    startX = 0f,
                    endX = Float.POSITIVE_INFINITY
                )
            )
        }
    }

}

@Composable
fun BottomGradientLayer(modifier: Modifier = Modifier, horizontalGradient: Brush) {
    Row(
        modifier = modifier
            .fillMaxHeight()
            .width(60.dp)
            .background(
                brush = horizontalGradient
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {}
}