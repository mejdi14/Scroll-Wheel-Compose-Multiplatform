package data

import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import scrollwheeldemo.composeapp.generated.resources.Res
import scrollwheeldemo.composeapp.generated.resources.image_1

data class ImageItem @OptIn(ExperimentalResourceApi::class)
constructor(
    val image: DrawableResource = Res.drawable.image_1,
    val size: Float = 75f,
    val rotationDegree: Float = 1.2f,
    val rotationExtraSpace: Int = 27,
    val scaleLeaningDegree: Int = 150,
    val spaceBetween: Int = 5,
    val cornerRadius: Int = 12,
)
