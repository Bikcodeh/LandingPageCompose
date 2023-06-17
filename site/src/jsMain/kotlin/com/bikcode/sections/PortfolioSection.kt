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
            if (breakpoint >= Breakpoint.MD) 100.percent else 90.percent
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
        PortfolioNavigation()
    }
}

@Composable
private fun PortfolioCards(breakpoint: Breakpoint) {
    Row(
        modifier = Modifier
            .id("scrollableContainer")
            .fillMaxWidth()
            .maxWidth(
                if (breakpoint > Breakpoint.MD) 950.px
                else if (breakpoint > Breakpoint.SM) 625.px
                else 300.px
            )
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
fun PortfolioNavigation() {
    val widthItem = 325.0
    var total by remember { mutableStateOf(widthItem * 3) }
    var availableLeft by remember { mutableStateOf(false) }
    var availableRight by remember { mutableStateOf(true) }
    val modifierEnabled = Modifier.cursor(Cursor.Pointer).color(Theme.Primary.rgb)
    val modifierDisabled = Modifier.disabled().color(Theme.Gray.rgb)
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        FaArrowLeft(
            modifier = Modifier
                .margin(right = 40.px)
                .thenIf(!availableLeft, modifierDisabled)
                .thenIf(availableLeft, modifierEnabled.onClick {
                    if (total > widthItem * 3) {
                        total -= widthItem
                        availableRight = true
                    }

                    if (total == widthItem * 3) {
                        availableLeft = false
                    }
                    document.getElementById("scrollableContainer")
                        ?.scrollBy(x = -(widthItem), y = 0.0)
                }),
            size = IconSize.LG
        )
        FaArrowRight(
            modifier = Modifier
                .thenIf(availableRight, modifierEnabled.onClick {
                    if (total != widthItem * 5)
                        total += widthItem
                    document.getElementById("scrollableContainer")
                        ?.scrollBy(x = widthItem, y = 0.0)
                    availableLeft = (total > widthItem * 3)
                    availableRight = (total >= widthItem * 5).not()
                })
                .thenIf(!availableRight, modifierDisabled),
            size = IconSize.LG
        )
    }
}