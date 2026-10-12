package com.github.hugohomesquita.htmxjetbrains

import com.intellij.psi.xml.XmlAttributeValue
import com.intellij.testFramework.fixtures.BasePlatformTestCase

class HxTargetReferenceTest : BasePlatformTestCase() {

    private fun resolveAtCaret(html: String) =
        myFixture.configureByText("test.html", html).let {
            myFixture.getReferenceAtCaretPositionWithAssertion().resolve()
        }

    fun testIdSelectorResolvesToTargetElement() {
        val target = resolveAtCaret("<div id=\"result\"></div><button hx-target=\"#res<caret>ult\"></button>")

        assertInstanceOf(target, XmlAttributeValue::class.java)
        assertEquals("result", (target as XmlAttributeValue).value)
    }

    fun testIdSelectorAfterExtendedSelectorResolves() {
        val target = resolveAtCaret("<div id=\"row-1\"></div><button hx-target=\"closest #row<caret>-1\"></button>")

        assertEquals("row-1", (target as XmlAttributeValue).value)
    }

    fun testUnknownIdDoesNotResolve() {
        assertNull(resolveAtCaret("<div id=\"result\"></div><button hx-target=\"#miss<caret>ing\"></button>"))
    }
}
