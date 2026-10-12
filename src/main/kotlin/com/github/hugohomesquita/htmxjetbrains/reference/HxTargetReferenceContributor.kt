package com.github.hugohomesquita.htmxjetbrains.reference

import com.intellij.openapi.util.TextRange
import com.intellij.patterns.XmlPatterns
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import com.intellij.psi.PsiReferenceBase
import com.intellij.psi.PsiReferenceContributor
import com.intellij.psi.PsiReferenceProvider
import com.intellij.psi.PsiReferenceRegistrar
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi.xml.XmlAttributeValue
import com.intellij.psi.xml.XmlTag
import com.intellij.util.ProcessingContext

class HxTargetReferenceContributor : PsiReferenceContributor() {
    override fun registerReferenceProviders(registrar: PsiReferenceRegistrar) {
        registrar.registerReferenceProvider(
            XmlPatterns.xmlAttributeValue().withParent(XmlPatterns.xmlAttribute("hx-target")),
            HxTargetReferenceProvider()
        )
    }
}

class HxTargetReferenceProvider : PsiReferenceProvider() {
    override fun getReferencesByElement(element: PsiElement, context: ProcessingContext): Array<PsiReference> {
        val attributeValue = element as? XmlAttributeValue ?: return PsiReference.EMPTY_ARRAY
        val valueOffset = attributeValue.valueTextRange.startOffset - attributeValue.textRange.startOffset

        return ID_SELECTOR.findAll(attributeValue.value).map {
            val id = it.groups[1]!!
            IdReference(attributeValue, TextRange(id.range.first, id.range.last + 1).shiftRight(valueOffset))
        }.toList().toTypedArray()
    }

    companion object {
        private val ID_SELECTOR = Regex("#([\\w-]+)")
    }
}

class IdReference(element: XmlAttributeValue, range: TextRange) :
    PsiReferenceBase<XmlAttributeValue>(element, range, true) {

    override fun resolve(): PsiElement? {
        val id = value
        return PsiTreeUtil.findChildrenOfType(element.containingFile, XmlTag::class.java)
            .firstNotNullOfOrNull { tag ->
                tag.getAttribute("id")?.valueElement?.takeIf { it.value == id }
            }
    }

    override fun getVariants(): Array<Any> = emptyArray()
}
