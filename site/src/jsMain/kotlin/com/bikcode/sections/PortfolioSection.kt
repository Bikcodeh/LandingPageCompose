package com.bikcode.sections

import androidx.compose.runtime.Composable
import com.bikcode.components.PortfolioCard
import com.bikcode.components.SectionTitle
import com.bikcode.models.Portfolio
import com.bikcode.models.Section
import com.bikcode.util.Constants
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.css.ScrollBehavior
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
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
    }
}

@Composable
private fun PortfolioCards(breakpoint: Breakpoint) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .maxWidth(
                if (breakpoint > Breakpoint.MD) 950.px
                else if (breakpoint > Breakpoint.SM) 625.px
                else 300.px
            )
            .overflow(Overflow.Hidden)
            .scrollBehavior(ScrollBehavior.Smooth)
    ) {
        Portfolio.values().take(
            if (breakpoint > Breakpoint.MD) 3
            else if (breakpoint > Breakpoint.SM) 2
            else 1
        )
            .forEach { portfolio ->
                PortfolioCard(portfolio = portfolio)
                if (portfolio != Portfolio.Five)
                    Box(Modifier.width(25.px))
            }
    }
}