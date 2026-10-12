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

        options.map { (key, value) ->
            LookupElementBuilder.create(key).withIcon(Htmx.ICON).withTypeText(value)
        }.forEach { result.addElement(it) }
    }

    companion object {
        val options = linkedMapOf(
            "innerHTML" to "The default, replace the inner html of the target element",
            "outerHTML" to "Replace the entire target element with the response",
            "textContent" to "Replace the text content of the target element, without parsing the response as HTML",
            "innerMorph" to "Morph the content inside the target element, preserving state and focus (htmx 4)",
            "outerMorph" to "Morph the entire target element, preserving state and focus (htmx 4)",
            "outerSync" to "Morph the target's attributes and replace its children, keeping the target element " +
                    "in the DOM so listeners and state are preserved (htmx 4)",
            "beforebegin" to "Insert the response before the target element",
            "afterbegin" to "Insert the response before the first child of the target element",
            "beforeend" to "Insert the response after the last child of the target element",
            "afterend" to "Insert the response after the target element",
            "before" to "Alias for beforebegin (htmx 4)",
            "prepend" to "Alias for afterbegin (htmx 4)",
            "append" to "Alias for beforeend (htmx 4)",
            "after" to "Alias for afterend (htmx 4)",
            "delete" to "Deletes the target element regardless of the response",
            "none" to "Does not append content from response (out of band items will still be processed)."
        )
    }
}
