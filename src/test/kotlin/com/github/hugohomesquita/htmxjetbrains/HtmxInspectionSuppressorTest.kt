package com.github.hugohomesquita.htmxjetbrains

import com.intellij.codeInsight.daemon.impl.analysis.XmlUnboundNsPrefixInspection
import com.intellij.testFramework.fixtures.BasePlatformTestCase

class HtmxInspectionSuppressorTest : BasePlatformTestCase() {

    override fun setUp() {
        super.setUp()
        myFixture.enableInspections(XmlUnboundNsPrefixInspection())
    }

    fun testHxStatusIsNotReportedAsUnboundNamespace() {
        myFixture.configureByText(
            "test.html",
            "<div hx-get=\"/x\" hx-status:404=\"swap:none\" hx-status:5xx:inherited=\"target:#errors\"></div>"
        )

        myFixture.checkHighlighting()
    }

    fun testOtherPrefixesAreStillReported() {
        myFixture.configureByText(
            "test.html",
            "<div <error descr=\"Namespace 'foo' is not bound\">foo</error>:bar=\"x\"></div>"
        )

        myFixture.checkHighlighting()
    }
}
