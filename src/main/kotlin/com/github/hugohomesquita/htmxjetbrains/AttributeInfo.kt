package com.github.hugohomesquita.htmxjetbrains

@Suppress("MemberVisibilityCanBePrivate")
class AttributeInfo(val attribute: String) {
    private val typeTexts = hashMapOf(
        "hx-boost" to "Add or remove progressive enhancement for links and forms",
        "hx-get" to "Issues a GET to the specified URL",
        "hx-post" to "Issues a POST to the specified URL",
        "hx-on" to "Handle any event with a script inline",
        "hx-push-url" to "Pushes the URL into the browser location bar, creating a new history entry",
        "hx-select" to "Select content to swap in from a response",
        "hx-select-oob" to "Select content to swap in from a response, out of band (somewhere other than the target)",
        "hx-swap" to "Controls how content is swapped in (outerHTML, beforeEnd, afterend, ...)",
        "hx-swap-oob" to "Marks content in a response to be out of band (should swap in somewhere other than the target)",
        "hx-target" to "Specifies the event that triggers the request",
        "hx-trigger" to "Specifies the event that triggers the request",
        "hx-vals" to "Adds values to the parameters to submit with the request (JSON-formatted)",
        "hx-confirm" to "Shows a confim() dialog before issuing a request",
        "hx-delete" to "Issues a DELETE to the specified URL",
        "hx-disable" to "Disables htmx processing for the given node and any children nodes",
        "hx-disabled-elt" to "Adds the disabled attribute to the specified elements while a request is in flight",
        "hx-disinherit" to "Control and disable automatic attribute inheritance for child nodes",
        "hx-encoding" to "Changes the request encoding type",
        "hx-ext" to "Extensions to use for this element",
        "hx-headers" to "Adds to the headers that will be submitted with the request",
        "hx-history" to "Prevent sensitive data being saved to the history cache",
        "hx-history-elt" to "The element to snapshot and restore during history navigation",
        "hx-include" to "Include additional data in requests",
        "hx-indicator" to "The element to put the htmx-request class on during the request",
        "hx-params" to "Filters the parameters that will be submitted with a request",
        "hx-patch" to "Issues a PATCH to the specified URL",
        "hx-preserve" to "Specifies elements to keep unchanged between requests",
        "hx-prompt" to "Shows a prompt() before submitting a request",
        "hx-put" to "Issues a PUT to the specified URL",
        "hx-replace-url" to "Replace the URL in the browser location bar",
        "hx-request" to "Configures various aspects of the request",
        "hx-sse" to "Has been moved to an extension.",
        "hx-sync" to "Control how requests made by different elements are synchronized",
        "hx-validate" to "Force elements to validate themselves before a request",
        "hx-vars" to "Adds values dynamically to the parameters to submit with the request (deprecated, please use hx-vals)",
        "hx-ws" to "Has been moved to an extension. Documentation for older versions",
    )

    val name: String

    val typeText: String

    init {
        name = attribute
        typeText = buildTypeText()
    }

    @Suppress("ComplexCondition")
    fun isHtmx(): Boolean {
        return this.isAttribute()
    }

    fun isAttribute(): Boolean {
        return AttributeUtil.attributes.contains(name)
    }

    fun hasValue(): Boolean {
        return name != ""
    }

    @Suppress("ReturnCount")
    private fun buildTypeText(): String {
        return typeTexts.getOrDefault(name, "Htmx")
    }
}
