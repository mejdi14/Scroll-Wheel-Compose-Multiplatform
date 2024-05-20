package ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import data.ImageItem
import helpers.dpToPx
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import kotlin.math.absoluteValue

@Composable
@OptIn(ExperimentalResourceApi::class)
fun BoxWithConstraintsScope.WheelListComposable(
    listState: LazyListState,
    listImages: List<ImageItem>
) {
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
}

