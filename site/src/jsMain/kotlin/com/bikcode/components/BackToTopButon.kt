package com.bikcode.components

import androidx.compose.runtime.*
import com.bikcode.models.Theme
import com.bikcode.styles.BackToTopStyle
import com.varabyte.kobweb.compose.css.CSSTransition
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.Visibility
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.icons.fa.FaArrowUp
import com.varabyte.kobweb.silk.components.icons.fa.IconSize
import com.varabyte.kobweb.silk.components.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.components.style.toModifier
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import kotlinx.browser.document
import kotlinx.browser.window
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.ms
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px

@Composable
fun BackToTopButton() {
    val breakpoint = rememberBreakpoint()
    var scroll: Double? by remember { mutableStateOf(null) }
    LaunchedEffect(Unit) {
        window.addEventListener(type = "scroll", callback = {
            scroll = document.documentElement?.scrollTop
        })
    }

    Column(
        modifier = Modifier.fillMaxSize().position(Position.Fixed).zIndex(1),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = BackToTopStyle.toModifier().size(50.px).visibility(
                if (scroll != null && scroll!! > 400.0) Visibility.Visible else Visibility.Hidden
            ).borderRadius(20.percent)
                .margin(
                    right = if(breakpoint <= Breakpoint.SM) 30.px else 40.px,
                    bottom = if(breakpoint <= Breakpoint.SM) 30.px else 40.px
                )
                .backgroundColor(Theme.Primary.rgb)
                .cursor(Cursor.Pointer)
                .transition(CSSTransition(property = "visibility", duration = 300.ms))
                .onClick {
                    document.documentElement?.scroll(x = 0.0, y = 0.0)
                }
        ) {
            FaArrowUp(
                modifier = Modifier.id("arrowUp").color(Colors.White),
                size = IconSize.LG
            )
        }
    }
}