package com.github.hugohomesquita.htmxjetbrains

import com.intellij.testFramework.fixtures.BasePlatformTestCase

class HtmxCompletionTest : BasePlatformTestCase() {

    private fun complete(html: String): List<String> {
        myFixture.configureByText("test.html", html)
        myFixture.completeBasic()
        return myFixture.lookupElementStrings ?: emptyList()
    }

    fun testAttributeCompletionIncludesHtmx4Attributes() {
        val lookups = complete("<div hx-<caret>></div>")

        assertContainsElements(
            lookups,
            "hx-get", "hx-post", "hx-swap", "hx-target",
            "hx-query", "hx-action", "hx-method", "hx-config", "hx-ignore", "hx-status",
            "hx-morph-skip", "hx-morph-skip-children", "hx-preload", "hx-pending",
        )
    }

    fun testAttributeCompletionKeepsHtmx2Attributes() {
        val lookups = complete("<div hx-<caret>></div>")

        assertContainsElements(lookups, "hx-disabled-elt", "hx-disinherit", "hx-ext", "hx-request")
    }

    fun testHxSwapValues() {
        val lookups = complete("<div hx-swap=\"<caret>\"></div>")

        assertContainsElements(
            lookups,
            "innerHTML", "outerHTML", "textContent", "innerMorph", "outerMorph", "outerSync",
            "beforebegin", "afterbegin", "beforeend", "afterend",
            "before", "prepend", "append", "after",
            "delete", "none",
        )
    }

    fun testHxSwapOobValues() {
        val lookups = complete("<div hx-swap-oob=\"<caret>\"></div>")

        assertContainsElements(lookups, "true", "outerHTML", "innerMorph", "beforeend", "append")
    }

    fun testHxTargetValues() {
        val lookups = complete("<div id=\"result\"></div><button hx-target=\"<caret>\"></button>")

        assertContainsElements(
            lookups,
            "this", "closest ", "find ", "findAll ", "next", "previous",
            "body", "document", "window", "host",
            "#result",
        )
    }

    fun testHxMethodValues() {
        val lookups = complete("<form hx-action=\"/x\" hx-method=\"<caret>\"></form>")

        assertContainsElements(lookups, "GET", "POST", "PUT", "PATCH", "DELETE", "QUERY")
    }
}
