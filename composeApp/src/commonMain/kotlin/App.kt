import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.imageResource
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

            )
        val listState = rememberLazyListState()
        val centerOfViewport = remember {
            { (listState.layoutInfo.viewportStartOffset + listState.layoutInfo.viewportEndOffset) / 2 }
        }

        LazyRow(
            state = listState,
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(listImages, key = { it }) { image ->
                val itemIndex = listImages.indexOf(image)
                val itemOffset = remember(
                    listState.firstVisibleItemIndex,
                    listState.firstVisibleItemScrollOffset
                ) {
                    calculateItemOffset(listState, itemIndex)
                }

                // Calculate rotation based on the item's offset from the center
                val rotation = if (itemOffset != null) {
                    (centerOfViewport() - itemOffset).coerceIn(-30f, 30f) / 6
                } else 0f

                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .graphicsLayer {
                            rotationZ = rotation
                        }
                        .then(Modifier.clip(shape = RoundedCornerShape(12.dp)))
                ) {
                    Image(
                        painter = painterResource(Res.drawable.image_1),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                    )
                }
                Spacer(Modifier.width(10.dp))
            }
        }
    }


}


private fun calculateItemOffset(listState: LazyListState, itemIndex: Int): Float? {
    val layoutInfo = listState.layoutInfo
    val visibleItems = layoutInfo.visibleItemsInfo
    val targetItem = visibleItems.find { it.index == itemIndex }
    return targetItem?.offset?.toFloat()?.plus(targetItem?.size?.toFloat() / 2)
}
