package com.bikcode.sections

import androidx.compose.runtime.*
import com.bikcode.components.AchievementCard
import com.bikcode.models.Achievement
import com.bikcode.models.Section
import com.bikcode.models.Theme
import com.bikcode.util.Constants
import com.bikcode.util.ObserveViewportEntered
import com.bikcode.util.animateNumbers
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import kotlinx.coroutines.launch
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
    val scope = rememberCoroutineScope()
    var viewportEntered by remember { mutableStateOf(false) }
    val animatedNumbers = remember { mutableStateListOf(0, 0, 0, 0) }
    ObserveViewportEntered(
        sectionId = Section.Achievements.id,
        distanceFromTop = 700.0,
        onViewportEntered = {
            viewportEntered = true
            Achievement.values().forEach { achievement ->
                scope.launch {
                    animateNumbers(
                        number = achievement.number,
                        onUpdate = {
                            animatedNumbers[achievement.ordinal] = it
                        }
                    )
                }
            }
        }
    )
    SimpleGrid(
        numColumns = numColumns(base = 1, md = 2, lg = 4)
    ) {
        Achievement.values().forEach { achievement ->
            AchievementCard(achievement = achievement, animatedNumber = animatedNumbers[achievement.ordinal])
        }
    }
}