package com.github.hugohomesquita.htmxjetbrains.completion

import com.github.hugohomesquita.htmxjetbrains.Htmx
import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionProvider
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.lang.html.HTMLLanguage
import com.intellij.util.ProcessingContext

class HxSwapCompletion : CompletionProvider<CompletionParameters?>() {
    override fun addCompletions(
        parameters: CompletionParameters,
        context: ProcessingContext,
        result: CompletionResultSet
    ) {
        val position = parameters.position

        if (HTMLLanguage.INSTANCE !in position.containingFile.viewProvider.languages) {
            return
        }

        val options = hashMapOf(
            "innerHTML" to "The default, replace the inner html of the target element",
            "outerHTML" to "Replace the entire target element with the response",
            "beforeBegin" to "Insert the response before the target element",
            "afterBegin" to "Insert the response before the first child of the target element",
            "beforeEnd" to "Insert the response after the last child of the target element",
            "afterEnd" to "Insert the response after the target element",
            "delete" to "Deletes the target element regardless of the response",
            "none" to "Does not append content from response (out of band items will still be processed)."
        )

        options.map { (key, value) ->
            LookupElementBuilder.create(key).withIcon(Htmx.ICON).withTypeText(value)
        }.forEach { result.addElement(it) }
    }
}