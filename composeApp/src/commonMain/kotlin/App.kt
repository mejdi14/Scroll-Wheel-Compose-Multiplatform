import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Spacer
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
import data.ImageItem
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import scrollwheeldemo.composeapp.generated.resources.Res
import scrollwheeldemo.composeapp.generated.resources.image_1
import scrollwheeldemo.composeapp.generated.resources.image_2
import scrollwheeldemo.composeapp.generated.resources.image_3
import scrollwheeldemo.composeapp.generated.resources.image_4
import scrollwheeldemo.composeapp.generated.resources.image_5
import scrollwheeldemo.composeapp.generated.resources.image_6
import scrollwheeldemo.composeapp.generated.resources.image_7
import scrollwheeldemo.composeapp.generated.resources.image_8
import ui.LeftSideShadowLines
import ui.RightSideShadowLines
import ui.SideGradientBackground
import kotlin.math.absoluteValue

@OptIn(ExperimentalResourceApi::class)
@Composable
@Preview
fun App() {
    MaterialTheme {
        val listImages = listOf(
            ImageItem(Res.drawable.image_1), ImageItem(Res.drawable.image_2),
            ImageItem(Res.drawable.image_3), ImageItem(Res.drawable.image_4),
            ImageItem(Res.drawable.image_5), ImageItem(Res.drawable.image_6),
            ImageItem(Res.drawable.image_7), ImageItem(Res.drawable.image_8),
            ImageItem(Res.drawable.image_1), ImageItem(Res.drawable.image_2),
            ImageItem(Res.drawable.image_3), ImageItem(Res.drawable.image_4),
            ImageItem(Res.drawable.image_5), ImageItem(Res.drawable.image_6),
            ImageItem(Res.drawable.image_7), ImageItem(Res.drawable.image_8),
            ImageItem(Res.drawable.image_1), ImageItem(Res.drawable.image_2),
            ImageItem(Res.drawable.image_1), ImageItem(Res.drawable.image_2),
        )
        val listState = rememberLazyListState()
        BoxWithConstraints {

            LazyRow(
                modifier = Modifier.fillMaxSize().background(color = Color.White),
                verticalAlignment = Alignment.CenterVertically,
                state = listState
            ) {
                itemsIndexed(listImages) { index, imageItem ->
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

                    val rotation =
                        dpToPx(halfScreenSize / imageItem.rotationExtraSpace) - (10f * (index - compositeState.value.first) - (compositeState.value.second / 25f))
                    val scale = (1f - ((rotation.absoluteValue) / imageItem.scaleLeaningDegree))
                    Box(
                        modifier = Modifier
                            .size(imageItem.size.dp)
                            .graphicsLayer {
                                rotationY = -(rotation * imageItem.rotationDegree)
                                scaleX = scale
                                scaleY = scale
                            }

                    ) {
                        Image(
                            painter = painterResource(imageItem.image),
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.clip(shape = RoundedCornerShape(imageItem.cornerRadius.dp))
                        )
                    }
                    Spacer(Modifier.width(imageItem.spaceBetween.dp))
                }
            }
            LeftSideShadowLines(modifier = Modifier.align(Alignment.CenterStart))
            RightSideShadowLines(modifier = Modifier.align(Alignment.CenterEnd))
        }
    }
}
