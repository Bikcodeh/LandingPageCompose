package com.bikcode.styles

import com.bikcode.models.Theme
import com.varabyte.kobweb.compose.css.CSSTransition
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.borderColor
import com.varabyte.kobweb.compose.ui.modifiers.borderWidth
import com.varabyte.kobweb.compose.ui.modifiers.transition
import com.varabyte.kobweb.silk.components.style.ComponentStyle
import com.varabyte.kobweb.silk.components.style.focus
import com.varabyte.kobweb.silk.components.style.hover
import org.jetbrains.compose.web.css.ms
import org.jetbrains.compose.web.css.px

val ContactSectionStyle by ComponentStyle {
    base {
        Modifier.borderColor(Theme.LighterGray.rgb)
            .transition(CSSTransition(property = "border-color", duration = 200.ms))
    }
    hover {
        Modifier.borderColor(Theme.Primary.rgb).borderWidth(2.px)
    }
    focus {
        Modifier.borderColor(Theme.Primary.rgb).borderWidth(2.px)
    }
}