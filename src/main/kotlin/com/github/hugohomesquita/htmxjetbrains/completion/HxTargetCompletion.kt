package com.github.hugohomesquita.htmxjetbrains.completion

import com.github.hugohomesquita.htmxjetbrains.Htmx
import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionProvider
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.completion.PrioritizedLookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.lang.html.HTMLLanguage
import com.intellij.psi.html.HtmlTag
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.util.ProcessingContext

class HxTargetCompletion : CompletionProvider<CompletionParameters?>() {
    override fun addCompletions(
        parameters: CompletionParameters,
        context: ProcessingContext,
        result: CompletionResultSet
    ) {
        val position = parameters.position

        if (HTMLLanguage.INSTANCE !in position.containingFile.viewProvider.languages) {
            return
        }

        val thisElement = LookupElementBuilder
            .create("this")
            .withIcon(Htmx.ICON)
            .withTypeText(
                "this which indicates that the element " +
                        "that the attribute is on is the target hx-target"
            )

        val closestElement = LookupElementBuilder
            .create("closest ")
            .withIcon(Htmx.ICON)
            .withTypeText(
                "closest <CSS selector> which will find the closest parent ancestor that " +
                        "matches the given CSS selector. (e.g. closest tr will target " +
                        "the closest table row to the element)"
            )

        val findElement = LookupElementBuilder
            .create("find ")
            .withIcon(Htmx.ICON)
            .withTypeText(
                "find <CSS selector> which will find the first child " +
                        "descendant element that matches the given CSS selector. (e.g find tr " +
                        "will target the first child descendant row to the element)"
            )

        result.addElement(PrioritizedLookupElement.withPriority(thisElement, 1.0))
        result.addElement(PrioritizedLookupElement.withPriority(closestElement, 1.0))
        result.addElement(PrioritizedLookupElement.withPriority(findElement, 1.0))

        val allTags = PsiTreeUtil.findChildrenOfType(position.containingFile, HtmlTag::class.java)
        allTags.forEach {
            val id = it.getAttribute("id")
            if (id != null) {
                val idValue = id.valueElement?.value
                if (idValue != null) {
                    val prefix = if (position.text.startsWith("#")) "" else "#" // if the user already typed #, don't add it again
                    result.addElement(
                        LookupElementBuilder
                            .create("$prefix$idValue")
                            .withIcon(Htmx.ICON)
                            .withTypeText("id of ${it.name}")
                    )
                }
            }
        }
    }
}