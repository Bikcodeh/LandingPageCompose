package com.bikcode.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.bikcode.models.Theme
import com.bikcode.styles.ContactSectionStyle
import com.bikcode.styles.MainButtonStyle
import com.bikcode.util.FormInput
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.attrsModifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.components.style.toModifier
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.*

@Composable
fun ContactForm() {
    val validator = remember {
        mutableListOf<FormInput>().apply {
            add(FormInput("inputName"))
            add(FormInput("inputEmail"))
            add(FormInput("inputMessage"))
        }
    }
    val breakpoint = rememberBreakpoint()
    Form(action = "") {
        Label(
            attrs = Modifier
                .classNames("form-label")
                .toAttrs(),
            forId = "inputName"
        ) {
            Text("Name")
        }
        Input(
           type = InputType.Text,
            attrs = ContactSectionStyle
                .toModifier()
                .id("inputName")
                .classNames("form-control")
                .width(
                    if (breakpoint >= Breakpoint.MD) 500.px else 250.px
                )
                .boxShadow(0.px, 0.px, 0.px, 0.px, null)
                .backgroundColor(Theme.LighterGray.rgb)
                .attrsModifier {
                    attr("placeholder", "Full name")
                    attr("name", "name")
                }
                .toAttrs()
        )
        Label(
            attrs = Modifier
                .classNames("form-label")
                .margin(top = 16.px)
                .toAttrs(),
            forId = "inputEmail"
        ) {
            Text("Email")
        }
        Input(
            type = InputType.Email,
            attrs = ContactSectionStyle
                .toModifier()
                .id("inputEmail")
                .classNames("form-control")
                .margin(top = 16.px)
                .width(
                    if (breakpoint >= Breakpoint.MD) 500.px else 250.px
                )
                .boxShadow(0.px, 0.px, 0.px, 0.px, null)
                .backgroundColor(Theme.LighterGray.rgb)
                .attrsModifier {
                    attr("placeholder", "Email address")
                    attr("name", "email")
                }
                .toAttrs()
        )
        Label(
            attrs = Modifier
                .classNames("form-label")
                .margin(top = 16.px)
                .toAttrs(),
            forId = "inputMessage"
        ) {
            Text("Message")
        }
        TextArea(
            attrs = ContactSectionStyle
                .toModifier()
                .id("inputMessage")
                .classNames("form-control")
                .height(150.px)
                .width(
                    if (breakpoint >= Breakpoint.MD) 500.px else 250.px
                )
                .boxShadow(0.px, 0.px, 0.px, 0.px, null)
                .backgroundColor(Theme.LighterGray.rgb)
                .attrsModifier {
                    attr("placeholder", "Your message here")
                    attr("name", "message")
                }
                .margin(bottom = 16.px)
                .toAttrs()
        )
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Button(
                attrs = MainButtonStyle.toModifier()
                    .height(40.px)
                    .border(width = 0.px)
                    .borderRadius(r = 5.px)
                    .backgroundColor(Theme.Primary.rgb)
                    .color(Colors.White)
                    .cursor(Cursor.Pointer)
                    .onClick {
                        validator.forEach { item -> item.validate() }
                        it.preventDefault()
                    }
                    .toAttrs()
            ) {
                Text("Submit")
            }
        }
    }
}