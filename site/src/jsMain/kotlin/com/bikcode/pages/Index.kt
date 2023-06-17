package com.bikcode.pages

import androidx.compose.runtime.*
import com.bikcode.sections.AboutSection
import com.bikcode.sections.MainSection
import com.bikcode.sections.PortfolioSection
import com.bikcode.sections.ServiceSection
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.core.Page

@Page
@Composable
fun HomePage() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        MainSection()
        AboutSection()
        ServiceSection()
        PortfolioSection()
    }
}
