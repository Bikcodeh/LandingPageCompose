package com.bikcode.util

import kotlinx.browser.document
import kotlinx.dom.addClass

data class FormInput(
    val id: String,
    val regex: Regex? = null
) {
    fun validate() {
        val element = document.getElementById(id)
        if ((element.asDynamic().value as String).isBlank()) {
            element?.addClass("is-invalid")
        } else {
            element?.classList?.contains("is-invalid").also {
                if (it == true) {
                    element?.classList?.replace("is-invalid", "is-valid")
                } else {
                    element?.classList?.remove("is-invalid")
                    element?.classList?.add("is-valid")
                }
            }

        }
    }
}
