package com.bikcode.pages

import androidx.compose.runtime.*
import com.bikcode.components.BackToTopButton
import com.bikcode.components.OverflowMenu
import com.bikcode.sections.*
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.core.Page

@Page
@Composable
fun HomePage() {
    var menuOpened by remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxSize()) {
        BackToTopButton()
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MainSection(onMenuClicked = { menuOpened = true })
            AboutSection()
            ServiceSection()
            PortfolioSection()
            AchievementSection()
            TestimonialSection()
            ExperienceSection()
            ContactSection()
            FooterSection()
        }
        if (menuOpened) {
            OverflowMenu(onMenuClosed = { menuOpened = false })
        }
    }
}
