package com.bikcode.sections

import androidx.compose.runtime.Composable
import com.bikcode.components.SectionTitle
import com.bikcode.components.SkillBar
import com.bikcode.models.Section
import com.bikcode.util.Constants
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.id
import com.varabyte.kobweb.compose.ui.modifiers.maxWidth
import com.varabyte.kobweb.compose.ui.modifiers.padding
import org.jetbrains.compose.web.css.px

@Composable
fun AboutSection() {
    Box(
        modifier = Modifier
            .id(Section.Home.id)
            .fillMaxWidth()
            .maxWidth(Constants.SECTION_WIDTH)
            .padding(topBottom = 15.px),
        contentAlignment = Alignment.Center
    ) {
       SectionTitle(section = Section.About)
    }
}