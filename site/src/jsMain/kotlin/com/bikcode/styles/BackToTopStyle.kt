package com.bikcode.styles

import com.varabyte.kobweb.compose.css.CSSTransition
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.rotate
import com.varabyte.kobweb.compose.ui.modifiers.transition
import com.varabyte.kobweb.silk.components.style.ComponentStyle
import com.varabyte.kobweb.silk.components.style.hover
import org.jetbrains.compose.web.css.deg
import org.jetbrains.compose.web.css.ms

val BackToTopStyle by ComponentStyle {
    cssRule(" > #arrowUp") {
        Modifier.rotate(a = 180.deg)
            .transition(CSSTransition(property = "rotate", duration = 200.ms))
    }

    cssRule(":hover > #arrowUp") {
        Modifier.rotate(a = 0.deg)
    }
}