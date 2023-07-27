package com.bikcode.components

import androidx.compose.runtime.*
import com.bikcode.models.Section
import com.bikcode.models.Theme
import com.bikcode.util.Constants
import com.bikcode.util.ObserveViewportEntered
import com.varabyte.kobweb.compose.css.CSSTransition
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.css.ms
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text

@Composable
fun SectionTitle(
    modifier: Modifier = Modifier,
    section: Section,
    alignment: Alignment.Horizontal = Alignment.Start
) {
    val scope = rememberCoroutineScope()
    var titleMargin by remember { mutableStateOf(50.px) }
    var subtitleMargin by remember { mutableStateOf(50.px) }

    ObserveViewportEntered(
        sectionId = section.id,
        distanceFromTop = 700.0,
        onViewportEntered = {
            scope.launch {
                subtitleMargin = 0.px
                if (alignment == Alignment.Start) {
                    delay(25)
                }
                titleMargin = 0.px
            }
        }
    )
    Column(modifier = modifier, horizontalAlignment = alignment) {
        P(
            attrs = Modifier
                .fillMaxWidth()
                .textAlign(
                    when (alignment) {
                        Alignment.CenterHorizontally -> TextAlign.Center
                        Alignment.End -> TextAlign.End
                        else -> TextAlign.Start
                    }
                )
                .margin(top = 0.px, bottom = 0.px, left = titleMargin)
                .fontFamily(Constants.FONT_FAMILY)
                .fontSize(25.px)
                .transition(CSSTransition(property = "margin", duration = 300.ms))
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
                    when (alignment) {
                        Alignment.CenterHorizontally -> TextAlign.Center
                        Alignment.End -> TextAlign.End
                        else -> TextAlign.Start
                    }
                )
                .margin(
                    bottom = 10.px,
                    top = 0.px,
                    left = if(alignment == Alignment.Start) subtitleMargin else 0.px,
                    right = if(alignment == Alignment.CenterHorizontally) subtitleMargin else 0.px
                )
                .fontFamily(Constants.FONT_FAMILY)
                .transition(CSSTransition(property = "margin", duration = 300.ms))
                .fontSize(40.px)
                .fontWeight(FontWeight.Bold)
                .color(Theme.Secondary.rgb)
                .toAttrs()
        ) {
            Text(section.subtitle)
        }
        Box(
            modifier = Modifier
                .height(2.px)
                .width(100.px)
                .borderRadius(r = 50.px)
                .backgroundColor(Theme.Primary.rgb)
        )

    }
}