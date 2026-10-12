package com.github.hugohomesquita.htmxjetbrains.completion

import com.github.hugohomesquita.htmxjetbrains.Htmx
import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionProvider
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.lang.html.HTMLLanguage
import com.intellij.util.ProcessingContext

class HxStatusCompletion : CompletionProvider<CompletionParameters?>() {
    override fun addCompletions(
        parameters: CompletionParameters,
        context: ProcessingContext,
        result: CompletionResultSet
    ) {
        val position = parameters.position

        if (HTMLLanguage.INSTANCE !in position.containingFile.viewProvider.languages) {
            return
        }

        val textBeforeCaret = position.text.substring(0, parameters.offset - position.textRange.startOffset)
        val pair = textBeforeCaret.substringAfterLast(' ').substringAfterLast(',')
        val separator = pair.indexOf(':')

        if (separator < 0) {
            options.map { (key, value) ->
                LookupElementBuilder.create(key).withIcon(Htmx.ICON).withTypeText(value)
            }.forEach { result.withPrefixMatcher(pair).addElement(it) }
            return
        }

        val values = valueOptions[pair.substring(0, separator + 1)] ?: return
        values.map { (key, value) ->
            LookupElementBuilder.create(key).withIcon(Htmx.ICON).withTypeText(value)
        }.forEach { result.withPrefixMatcher(pair.substring(separator + 1)).addElement(it) }
    }

    companion object {
        val options = linkedMapOf(
            "swap:" to "Swap style for this status code, accepts the hx-swap values (e.g. swap:none)",
            "target:" to "CSS selector for the swap target, overrides hx-target (e.g. target:#errors)",
            "select:" to "CSS selector to choose content from the response, overrides hx-select (e.g. select:#msg)",
            "push:" to "Pushes a URL to browser history, accepts true, false or a URL (e.g. push:/items/42)",
            "replace:" to "Replaces the current history URL, accepts true, false or a URL (e.g. replace:/err)",
            "transition:" to "Uses the View Transitions API for the swap, accepts true or false",
        )

        private val booleanOptions = linkedMapOf(
            "true" to "Enable",
            "false" to "Disable",
        )

        private val valueOptions = mapOf(
            "swap:" to HxSwapCompletion.options,
            "push:" to booleanOptions,
            "replace:" to booleanOptions,
            "transition:" to booleanOptions,
        )
    }
}
