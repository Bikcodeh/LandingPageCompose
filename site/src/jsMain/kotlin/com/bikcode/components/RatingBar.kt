package com.bikcode.components

import androidx.compose.runtime.Composable
import com.bikcode.util.Res
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.size
import com.varabyte.kobweb.silk.components.graphics.Image
import org.jetbrains.compose.web.css.px
import kotlin.math.ceil
import kotlin.math.floor

@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Double = 0.0,
    stars: Int = 5,
    starsColor: Color.Rgb = Colors.Yellow,
) {
    val voteAverage = if (rating > 5.0) 5.0 else rating
    val filledStars = floor(voteAverage).toInt()
    val unfilledStars = (stars - ceil(voteAverage)).toInt()
    val halfStar = !(voteAverage.rem(1).equals(0.0))
    val modifierStar = Modifier.size(24.px).color(starsColor)

    Row(modifier = modifier) {
        repeat(filledStars) {
            Image(
                src = Res.Image.starFilled,
                desc = "",
                modifier = modifierStar
            )
        }

        if (halfStar) {
            Image(
                src = Res.Image.starHalf,
                desc = "",
                modifier = modifierStar
            )
        }

        repeat(unfilledStars) {
            Image(
                src = Res.Image.starOutline,
                desc = "",
                modifier = modifierStar
            )
        }
    }
}