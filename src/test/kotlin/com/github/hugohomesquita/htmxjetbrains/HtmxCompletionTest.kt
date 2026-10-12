package com.github.hugohomesquita.htmxjetbrains

import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi.xml.XmlAttribute
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

    fun testAttributeCompletionIncludesHxStatusCodes() {
        val lookups = complete("<div hx-<caret>></div>")

        assertContainsElements(lookups, "hx-status", "hx-status:4xx", "hx-status:5xx", "hx-status:404", "hx-status:500")
    }

    fun testAttributeCompletionOnHxStatusPrefix() {
        val lookups = complete("<div hx-status<caret>></div>")

        assertContainsElements(lookups, "hx-status", "hx-status:4xx", "hx-status:5xx", "hx-status:404", "hx-status:500")
    }

    fun testHxStatusValues() {
        val lookups = complete("<div hx-status:404=\"<caret>\"></div>")

        assertContainsElements(lookups, "swap:", "target:", "select:", "push:", "replace:", "transition:")
    }

    fun testHxStatusWildcardValues() {
        val lookups = complete("<div hx-status:5xx=\"<caret>\"></div>")

        assertContainsElements(lookups, "swap:", "target:", "select:", "push:", "replace:", "transition:")
    }

    fun testHxStatusWithoutCodeValues() {
        val lookups = complete("<div hx-status=\"<caret>\"></div>")

        assertContainsElements(lookups, "swap:", "target:", "select:", "push:", "replace:", "transition:")
    }

    fun testHxStatusAttributeIsRecognized() {
        myFixture.configureByText("test.html", "<div hx-status:404=\"swap:none\" hx-status:50x=\"select:#x\"></div>")
        val attributes = PsiTreeUtil.findChildrenOfType(myFixture.file, XmlAttribute::class.java)

        assertEquals(listOf("hx-status:404", "hx-status:50x"), attributes.map { it.name })
        attributes.forEach {
            assertTrue(it.name, it.descriptor is HtmxAttributeDescriptor)
        }
        assertFalse(AttributeInfo("hx-status:abc").isHtmx())
    }
}
