package com.github.hugohomesquita.htmxjetbrains

import com.intellij.psi.html.HtmlTag

class AutoCompleteSuggestions(val htmlTag: HtmlTag, val partialAttribute: String) {

    val descriptors: MutableList<AttributeInfo> = mutableListOf()

    init {
        addAttributes()
    }

    private fun addAttributes() {
        for (directive in AttributeUtil.attributes) {
            descriptors.add(AttributeInfo(directive))
        }
    }
}
