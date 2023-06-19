package com.bikcode.sections

import androidx.compose.runtime.Composable
import com.bikcode.components.AchievementCard
import com.bikcode.models.Achievement
import com.bikcode.models.Section
import com.bikcode.models.Theme
import com.bikcode.util.Constants
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import org.jetbrains.compose.web.css.px

@Composable
fun AchievementSection() {
    Box(
        modifier = Modifier
            .id(Section.Achievements.id)
            .fillMaxWidth()
            .maxWidth(Constants.SECTION_WIDTH)
            .padding(topBottom = 100.px)
            .backgroundColor(Theme.LighterGray.rgb),
        contentAlignment = Alignment.Center
    ) {
        AchievementContent()
    }
}

@Composable
private fun AchievementContent() {
    SimpleGrid(
        numColumns = numColumns(base = 1, md = 2, lg = 4)
    ) {
        Achievement.values().forEach { achievement ->
            AchievementCard(achievement = achievement)
        }
    }
}