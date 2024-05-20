import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import data.ImageItem
import org.jetbrains.compose.resources.ExperimentalResourceApi
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
import ui.WheelListComposable

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

            WheelListComposable(listState, listImages)
            LeftSideShadowLines(modifier = Modifier.align(Alignment.CenterStart))
            RightSideShadowLines(modifier = Modifier.align(Alignment.CenterEnd))
        }
    }
}


