package com.bikcode.components

import androidx.compose.runtime.Composable
import com.bikcode.styles.SocialLinkStyle
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.minWidth
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.silk.components.icons.fa.*
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.style.toModifier
import org.jetbrains.compose.web.css.px

@Composable
fun SocialBar() {
    Column(
        modifier = Modifier.margin(right = 25.px)
            .padding(topBottom = 25.px)
            .minWidth(40.px)
            .borderRadius(r = 20.px)
            .backgroundColor(Colors.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SocialLinks()
    }
}

@Composable
private fun SocialLinks() {
    Link(
        path = "https://google.com"
    ) {
        FaFacebook(
            modifier = SocialLinkStyle.toModifier(),
            size = IconSize.LG
        )
    }
    Box(modifier = Modifier.margin(bottom = 30.px))
    Link(
        path = "https://google.com"
    ) {
        FaTwitter(
            modifier = SocialLinkStyle.toModifier(),
            size = IconSize.LG
        )
    }
    Box(modifier = Modifier.margin(bottom = 30.px))
    Link(
        path = "https://google.com"
    ) {
        FaLinkedin(
            modifier = SocialLinkStyle.toModifier(),
            size = IconSize.LG
        )
    }
    Box(modifier = Modifier.margin(bottom = 30.px))
    Link(
        path = "https://google.com"
    ) {
        FaInstagram(
            modifier = SocialLinkStyle.toModifier(),
            size = IconSize.LG
        )
    }
}