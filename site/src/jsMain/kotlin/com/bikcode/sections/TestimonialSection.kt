package com.bikcode.sections

import androidx.compose.runtime.*
import com.bikcode.components.SectionTitle
import com.bikcode.components.TestimonialCard
import com.bikcode.models.Section
import com.bikcode.models.Testimonial
import com.bikcode.models.Theme
import com.bikcode.util.Constants
import com.bikcode.util.animateNumbers
import com.varabyte.kobweb.compose.css.CSSTransition
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.Visibility
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import org.jetbrains.compose.web.css.ms
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import kotlin.math.ceil

@Composable
fun TestimonialSection() {
    val breakpoint = rememberBreakpoint()
    Box(
        modifier = Modifier
            .id(Section.Testimonial.id)
            .fillMaxWidth()
            .maxWidth(Constants.SECTION_WIDTH)
            .padding(topBottom = 100.px)
            .backgroundColor(Theme.LighterGray.rgb),
        contentAlignment = Alignment.Center
    ) {
        TestimonialContent(breakpoint)
    }
}

@Composable
private fun TestimonialContent(breakpoint: Breakpoint) {
    var selectedPage by remember { mutableStateOf(0) }
    Column(
        modifier = Modifier
            .fillMaxWidth(
                if (breakpoint >= Breakpoint.MD) 100.percent
                else 90.percent
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SectionTitle(
            modifier = Modifier
                .fillMaxWidth()
                .margin(bottom = 25.px),
            section = Section.Testimonial,
            alignment = Alignment.CenterHorizontally
        )
        TestimonialCards(
            breakpoint,
            getPair(selectedPage),
            true
        )
        TestimonialNavigation(
            selectedPage = selectedPage,
            onNavigate = { selectedPage = it }
        )
    }
}

@Composable
fun TestimonialCards(
    breakpoint: Breakpoint, pair: Pair<Testimonial?, Testimonial?>, visible: Boolean
) {
    val animatedNumbers = remember { mutableStateListOf(0) }
    LaunchedEffect(pair) {
        animateNumbers(100, onUpdate = {
            animatedNumbers[0] = it
        })
    }
    SimpleGrid(
        modifier = Modifier.margin(bottom = 40.px),
        numColumns = numColumns(base = 1, md = 2)
    ) {
        Box {
            pair.first?.let {
                TestimonialCard(
                    modifier = Modifier.margin(
                        right = if (breakpoint > Breakpoint.SM) 40.px else 0.px,
                        bottom = if (breakpoint > Breakpoint.MD) 0.px else 40.px,
                    ).visibility(if (visible) Visibility.Visible else Visibility.Hidden)
                        .opacity(animatedNumbers[0].percent)
                        .transition(CSSTransition(property = "visibility", duration = 600.ms)),
                    testimonial = it,
                    breakpoint = breakpoint
                )
            }
        }
        Box {
            pair.second?.let {
                TestimonialCard(
                    modifier = Modifier.margin(
                        right = if (breakpoint > Breakpoint.SM) 40.px else 0.px,
                        bottom = if (breakpoint > Breakpoint.MD) 0.px else 40.px,
                    ).opacity(animatedNumbers[0].percent)
                        .visibility(if (visible) Visibility.Visible else Visibility.Hidden)
                        .transition(CSSTransition(property = "visibility", duration = 600.ms)),
                    testimonial = it,
                    breakpoint = breakpoint
                )
            }
        }
    }
}

@Composable
fun getPair(index: Int): Pair<Testimonial?, Testimonial?> {
    val totalItems = Testimonial.values().count()
    val startIndex = index * 2
    if (startIndex >= totalItems) {
        return Pair(null, null)
    }

    val endIndex = startIndex + 1
    val item1 = Testimonial.values().toList()[startIndex]
    val item2 = if (endIndex < totalItems) Testimonial.values().toList()[endIndex] else null

    return Pair(item1, item2)
}

@Composable
fun TestimonialNavigation(
    selectedPage: Int,
    onNavigate: (Int) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(ceil((Testimonial.values().count().toDouble() / 2.0)).toInt()) { index ->
            Box(
                modifier = Modifier
                    .margin(right = 10.px)
                    .cursor(Cursor.Pointer)
                    .size(12.px)
                    .borderRadius(50.px)
                    .backgroundColor(
                        if (selectedPage == index) Theme.Primary.rgb else Theme.LightGray.rgb
                    )
                    .onClick { onNavigate(index) }
            )
        }
    }
}