package com.github.hugohomesquita.htmxjetbrains.completion

import com.github.hugohomesquita.htmxjetbrains.Htmx
import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionProvider
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.lang.html.HTMLLanguage
import com.intellij.util.ProcessingContext

class HxMethodCompletion : CompletionProvider<CompletionParameters?>() {
    override fun addCompletions(
        parameters: CompletionParameters,
        context: ProcessingContext,
        result: CompletionResultSet
    ) {
        val position = parameters.position

        if (HTMLLanguage.INSTANCE !in position.containingFile.viewProvider.languages) {
            return
        }

        val options = linkedMapOf(
            "GET" to "Issues a GET to the hx-action URL",
            "POST" to "Issues a POST to the hx-action URL",
            "PUT" to "Issues a PUT to the hx-action URL",
            "PATCH" to "Issues a PATCH to the hx-action URL",
            "DELETE" to "Issues a DELETE to the hx-action URL",
            "QUERY" to "Issues a QUERY to the hx-action URL (params in body)",
        )

        options.map { (key, value) ->
            LookupElementBuilder.create(key).withIcon(Htmx.ICON).withTypeText(value)
        }.forEach { result.addElement(it) }
    }
}
