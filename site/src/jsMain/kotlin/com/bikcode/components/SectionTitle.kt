package com.bikcode.components

import androidx.compose.runtime.Composable
import com.bikcode.models.Section
import com.bikcode.models.Theme
import com.bikcode.util.Constants
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text

@Composable
fun SectionTitle(
    modifier: Modifier = Modifier,
    section: Section,
    alignment: Alignment.Horizontal = Alignment.Start
) {
    Column(modifier = modifier, horizontalAlignment = alignment) {
        P(
            attrs = Modifier
                .fillMaxWidth()
                .textAlign(
                    if (alignment == Alignment.CenterHorizontally) TextAlign.Center else TextAlign.Start
                )
                .margin(topBottom = 0.px)
                .fontFamily(Constants.FONT_FAMILY)
                .fontSize(25.px)
                .fontWeight(FontWeight.Normal)
                .color(Theme.Primary.rgb)
                .toAttrs()
        ) {
            Text(section.title)
        }
        P(
            attrs = Modifier
                .fillMaxWidth()
                .textAlign(
                    if (alignment == Alignment.CenterHorizontally) TextAlign.Center else TextAlign.Start
                )
                .margin(bottom = 10.px, top = 0.px)
                .fontFamily(Constants.FONT_FAMILY)
                .fontSize(40.px)
                .fontWeight(FontWeight.Bold)
                .color(Theme.Secondary.rgb)
                .toAttrs()
        ) {
            Text(section.subtitle)
        }
        Box(modifier = Modifier
            .height(2.px)
            .width(100.px)
            .borderRadius(r = 50.px)
            .backgroundColor(Theme.Primary.rgb)
        )

    }
}