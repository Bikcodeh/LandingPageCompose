package com.bikcode.sections

import androidx.compose.runtime.*
import com.bikcode.components.PortfolioCard
import com.bikcode.components.SectionTitle
import com.bikcode.models.Portfolio
import com.bikcode.models.Section
import com.bikcode.models.Theme
import com.bikcode.util.Constants
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.css.ScrollBehavior
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.thenIf
import com.varabyte.kobweb.silk.components.icons.fa.FaArrowLeft
import com.varabyte.kobweb.silk.components.icons.fa.FaArrowRight
import com.varabyte.kobweb.silk.components.icons.fa.IconSize
import com.varabyte.kobweb.silk.components.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.FlexWrap
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px

@Composable
fun PortfolioSection() {
    Box(
        modifier = Modifier
            .id(Section.Portfolio.id)
            .fillMaxWidth()
            .maxWidth(Constants.SECTION_WIDTH)
            .padding(topBottom = 100.px),
        contentAlignment = Alignment.Center
    ) {
        PortfolioContent()
    }
}

@Composable
private fun PortfolioContent() {
    val breakpoint = rememberBreakpoint()
    Column(
        modifier = Modifier.fillMaxWidth(
            //if (breakpoint >= Breakpoint.MD) 100.percent else 90.percent
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SectionTitle(
            alignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth().margin(bottom = 25.px),
            section = Section.Portfolio
        )
        PortfolioCards(breakpoint)
        Box(modifier = Modifier.height(20.px))
        PortfolioNavigation(breakpoint)
    }
}

@Composable
private fun PortfolioCards(breakpoint: Breakpoint) {
    var width by remember {
        mutableStateOf(
            when (breakpoint) {
                Breakpoint.MD, Breakpoint.LG -> 325 * 2
                Breakpoint.SM -> 325
                else -> 3 * 325
            }
        )
    }

    LaunchedEffect(breakpoint) {
        width = when (breakpoint) {
            Breakpoint.MD, Breakpoint.LG -> 325 * 2
            Breakpoint.SM -> 325
            else -> 3 * 325
        }
    }
    Row(
        modifier = Modifier
            .id("scrollableContainer")
            .width(width.px)
            .minWidth(325.px)
            .overflow(Overflow.Hidden)
            .display(DisplayStyle.Flex)
            .flexWrap(FlexWrap.Nowrap)
            .scrollBehavior(ScrollBehavior.Smooth)
    ) {
        Portfolio.values()
            .forEach { portfolio ->
                PortfolioCard(portfolio = portfolio)
            }
    }
}

@Composable
fun PortfolioNavigation(breakpoint: Breakpoint) {
    val widthItem = 325.0
    val totalItems by remember {
        mutableStateOf(
            when (breakpoint) {
                Breakpoint.MD, Breakpoint.LG -> 2
                Breakpoint.SM -> 1
                else -> 3
            }
        )
    }
    val scope = rememberCoroutineScope()
    var total by remember { mutableStateOf(widthItem * totalItems) }
    var buttonLeftEnabled by remember { mutableStateOf(true) }
    var buttonRightEnabled by remember { mutableStateOf(true) }
    var availableLeft by remember { mutableStateOf(false) }
    var availableRight by remember { mutableStateOf(true) }
    val modifierEnabled = Modifier.cursor(Cursor.Pointer).color(Theme.Primary.rgb)
    val modifierDisabled = Modifier.disabled().color(Theme.Gray.rgb)


    LaunchedEffect(true) {
        document.getElementById("scrollableContainer")?.scrollBy(x = -window.scrollX, y = -window.scrollY)
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        FaArrowLeft(
            modifier = Modifier
                .margin(right = 40.px)
                .thenIf(!availableLeft, modifierDisabled)
                .thenIf(availableLeft && buttonLeftEnabled, modifierEnabled.onClick {
                    if (buttonLeftEnabled) {
                        buttonLeftEnabled = false
                        println(total)
                        if (total > widthItem * totalItems) {
                            total -= widthItem
                            availableRight = true
                        }

                        if (total == widthItem * totalItems) {
                            availableLeft = false
                        }
                        document.getElementById("scrollableContainer")
                            ?.scrollBy(x = -(widthItem), y = 0.0)
                    }
                    scope.launch {
                        delay(1000)
                        buttonLeftEnabled = true
                    }
                })
                .thenIf(availableLeft, modifierEnabled),
            size = IconSize.LG
        )
        FaArrowRight(
            modifier = Modifier
                .thenIf(availableRight && buttonRightEnabled, modifierEnabled.onClick {
                    if (buttonRightEnabled) {
                        if (total < widthItem * Portfolio.values().count())
                            total += widthItem
                        document.getElementById("scrollableContainer")
                            ?.scrollBy(x = widthItem, y = 0.0)
                        availableLeft = ((total > (widthItem * totalItems)))
                        availableRight = (total != widthItem * Portfolio.values().count())
                        buttonRightEnabled = false
                    }
                    scope.launch {
                        delay(1000)
                        buttonRightEnabled = true
                    }
                })
                .thenIf(!availableRight, modifierDisabled)
                .thenIf(availableRight, modifierEnabled),
            size = IconSize.LG
        )
    }
}