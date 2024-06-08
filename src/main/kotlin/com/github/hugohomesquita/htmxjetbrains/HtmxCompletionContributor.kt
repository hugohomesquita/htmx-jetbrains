package com.github.hugohomesquita.htmxjetbrains

import com.github.hugohomesquita.htmxjetbrains.completion.HxSwapCompletion
import com.github.hugohomesquita.htmxjetbrains.completion.HxSwapOobCompletion
import com.github.hugohomesquita.htmxjetbrains.completion.HxTargetCompletion
import com.intellij.codeInsight.completion.CompletionContributor
import com.intellij.codeInsight.completion.CompletionType
import com.intellij.patterns.PlatformPatterns
import com.intellij.patterns.XmlPatterns
import com.intellij.psi.xml.XmlTokenType

class HtmxCompletionContributor : CompletionContributor() {
    init {
        extend(
            CompletionType.BASIC,
            PlatformPatterns.psiElement(XmlTokenType.XML_NAME).withParent(XmlPatterns.xmlAttribute()),
            HtmxAttributeCompletionProvider()
        )
        extend(
            CompletionType.BASIC,
            PlatformPatterns
                .psiElement(XmlTokenType.XML_ATTRIBUTE_VALUE_TOKEN)
                .inside(XmlPatterns.xmlAttribute("hx-target")),
            HxTargetCompletion()
        )
        extend(
            CompletionType.BASIC,
            PlatformPatterns
                .psiElement(XmlTokenType.XML_ATTRIBUTE_VALUE_TOKEN)
                .inside(XmlPatterns.xmlAttribute("hx-swap")),
            HxSwapCompletion()
        )
        extend(
            CompletionType.BASIC,
            PlatformPatterns
                .psiElement(XmlTokenType.XML_ATTRIBUTE_VALUE_TOKEN)
                .inside(XmlPatterns.xmlAttribute("hx-swap-oob")),
            HxSwapOobCompletion()
        )
    }
}