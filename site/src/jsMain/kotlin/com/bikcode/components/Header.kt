package com.bikcode.components

import androidx.compose.runtime.Composable
import com.bikcode.models.Section
import com.bikcode.models.Theme
import com.bikcode.styles.LogoStyle
import com.bikcode.styles.NavigationItemStyle
import com.bikcode.util.Constants.FONT_FAMILY
import com.bikcode.util.Res
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.TextDecorationLine
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.foundation.layout.Spacer
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.icons.fa.FaBars
import com.varabyte.kobweb.silk.components.icons.fa.IconSize
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.components.style.toModifier
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px

@Composable
fun Header(
    onMenuClicked: () -> Unit
) {
    val breakpoint = rememberBreakpoint()
    Row(
        modifier = Modifier.fillMaxWidth(if (breakpoint > Breakpoint.MD) 80.percent else 90.percent)
            .margin(topBottom = 50.px),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        LeftSide(breakpoint = breakpoint, onMenuClicked = onMenuClicked)
        if (breakpoint > Breakpoint.MD) {
            RightSide()
        }
    }
}

@Composable
fun LeftSide(
    onMenuClicked: () -> Unit,
    breakpoint: Breakpoint
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        if (breakpoint <= Breakpoint.MD) {
            FaBars(
                modifier = Modifier
                    .margin(right = 15.px)
                    .cursor(Cursor.Pointer)
                    .onClick { onMenuClicked() },
                size = IconSize.XL
            )
        }
        Image(
            modifier = LogoStyle.toModifier(),
            src = Res.Image.logo,
            desc = "Logo image"
        )
    }
}

@Composable
fun RightSide() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .borderRadius(r = 50.px)
            .backgroundColor(Theme.LighterGray.rgb)
            .padding(all = 20.px),
        horizontalArrangement = Arrangement.End
    ) {
        Section.values().take(6).forEach { section ->
            Link(
                modifier = NavigationItemStyle.toModifier()
                    .fontFamily(FONT_FAMILY)
                    .fontSize(18.px)
                    .fontWeight(FontWeight.Normal)
                    .textDecorationLine(textDecorationLine = TextDecorationLine.None),
                path = section.path,
                text = section.title
            )
            Box(modifier = Modifier.padding(right = 30.px))
        }
    }
}