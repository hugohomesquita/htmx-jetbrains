package com.github.hugohomesquita.htmxjetbrains

import com.intellij.psi.html.HtmlTag

class AutoCompleteSuggestions(val htmlTag: HtmlTag, val partialAttribute: String) {

    val attributes: MutableList<AttributeInfo> = mutableListOf()

    init {
        addAttributes()
    }

    private fun addAttributes() {
        (AttributeUtil.attributes + AttributeUtil.statusAttributes).mapTo(attributes) { AttributeInfo(it) }
    }
}
