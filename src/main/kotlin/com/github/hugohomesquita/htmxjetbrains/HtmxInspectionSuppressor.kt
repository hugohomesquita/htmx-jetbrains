package com.github.hugohomesquita.htmxjetbrains

import com.intellij.codeInspection.InspectionSuppressor
import com.intellij.codeInspection.SuppressQuickFix
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi.xml.XmlAttribute

class HtmxInspectionSuppressor : InspectionSuppressor {
    override fun isSuppressedFor(element: PsiElement, toolId: String): Boolean {
        if (toolId != UNBOUND_NAMESPACE_INSPECTION) {
            return false
        }
        val attribute = PsiTreeUtil.getParentOfType(element, XmlAttribute::class.java, false) ?: return false
        return attribute.namespacePrefix.startsWith("hx-", ignoreCase = true)
    }

    override fun getSuppressActions(element: PsiElement?, toolId: String): Array<SuppressQuickFix> {
        return SuppressQuickFix.EMPTY_ARRAY
    }

    companion object {
        private const val UNBOUND_NAMESPACE_INSPECTION = "XmlUnboundNsPrefix"
    }
}
