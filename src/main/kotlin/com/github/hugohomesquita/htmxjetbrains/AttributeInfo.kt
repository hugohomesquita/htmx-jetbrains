package com.github.hugohomesquita.htmxjetbrains

@Suppress("MemberVisibilityCanBePrivate")
class AttributeInfo(val attribute: String) {
    private val typeTexts = hashMapOf(
        // Core attributes (htmx 2 and htmx 4)
        "hx-boost" to "Add or remove progressive enhancement for links and forms",
        "hx-get" to "Issues a GET to the specified URL",
        "hx-post" to "Issues a POST to the specified URL",
        "hx-put" to "Issues a PUT to the specified URL",
        "hx-patch" to "Issues a PATCH to the specified URL",
        "hx-delete" to "Issues a DELETE to the specified URL",
        "hx-on" to "Handle any event with a script inline",
        "hx-push-url" to "Pushes the URL into the browser location bar, creating a new history entry",
        "hx-replace-url" to "Replace the URL in the browser location bar",
        "hx-select" to "Select content to swap in from a response",
        "hx-select-oob" to "Select content to swap in from a response, out of band (somewhere other than the target)",
        "hx-swap" to "Controls how content is swapped in (innerHTML, outerHTML, innerMorph, beforeend, ...)",
        "hx-swap-oob" to "Marks content in a response to be out of band (should swap in somewhere other than the target)",
        "hx-target" to "Specifies the target element to be swapped",
        "hx-trigger" to "Specifies the event that triggers the request",
        "hx-vals" to "Adds values to the request parameters (JSON object, or js: prefix to evaluate JavaScript)",
        "hx-confirm" to "Shows a confirm() dialog before issuing a request",
        "hx-disable" to "Adds the disabled attribute to the selected elements while a request is in flight " +
                "(htmx 4, was hx-disabled-elt; in htmx 2 it disabled htmx processing, now hx-ignore)",
        "hx-encoding" to "Changes the request encoding type (multipart/form-data; default is application/x-www-form-urlencoded)",
        "hx-headers" to "Adds headers to the request (JSON object, or js: prefix to evaluate JavaScript)",
        "hx-history-elt" to "The element to snapshot and restore during history navigation",
        "hx-include" to "Include additional data in requests",
        "hx-indicator" to "The element to put the htmx-request class on during the request",
        "hx-preserve" to "Keeps this element (matched by id) unchanged when an ancestor is swapped",
        "hx-sync" to "Control how requests made by different elements are synchronized (and queued)",
        "hx-validate" to "Force elements to validate themselves before a request",
        // Added in htmx 4
        "hx-query" to "Issues a QUERY to the specified URL, sending parameters in the body (htmx 4)",
        "hx-action" to "Specifies the URL that receives the request, used with hx-method (htmx 4)",
        "hx-method" to "Specifies the HTTP method for the request, overriding method/formmethod (htmx 4)",
        "hx-config" to "Configures request behavior per element, as key:value (HCON) or JSON, " +
                "e.g. timeout:5s (htmx 4, replaces hx-request)",
        "hx-ignore" to "Disables htmx processing for the given node and any children nodes (htmx 4, was hx-disable)",
        "hx-morph-skip" to "Freezes an element during a morph swap (htmx 4)",
        "hx-morph-skip-children" to "Morphs an element's attributes but keeps its children (htmx 4)",
        "hx-preload" to "Preloads content before the user triggers a request (htmx 4 extension)",
        "hx-pending" to "Shows custom content while a request is in progress (htmx 4 extension)",
        // htmx 2 only (removed, renamed or moved to extensions in htmx 4)
        "hx-disabled-elt" to "Adds the disabled attribute to the specified elements while a request is in flight " +
                "(htmx 2, renamed to hx-disable in htmx 4)",
        "hx-disinherit" to "Control and disable automatic attribute inheritance for child nodes " +
                "(htmx 2, removed in htmx 4: use the :inherited modifier)",
        "hx-inherit" to "Re-enable automatic attribute inheritance for child nodes when disabled by default " +
                "(htmx 2, removed in htmx 4: use the :inherited modifier)",
        "hx-ext" to "Extensions to use for this element (htmx 2, removed in htmx 4: include extension scripts directly)",
        "hx-history" to "Prevent sensitive data being saved to the history cache " +
                "(htmx 2; in htmx 4 only honoured by the hx-history-cache extension)",
        "hx-params" to "Filters the parameters that will be submitted with a request " +
                "(htmx 2, removed in htmx 4: filter params in the htmx:config:request event)",
        "hx-prompt" to "Shows a prompt() before submitting a request (core in htmx 2, requires the hx-prompt extension in htmx 4)",
        "hx-request" to "Configures various aspects of the request (htmx 2, replaced by hx-config in htmx 4)",
        "hx-sse" to "Legacy htmx 1 attribute, replaced by the SSE extension " +
                "(sse-connect/sse-swap in htmx 2, hx-sse:connect in htmx 4)",
        "hx-vars" to "Adds values dynamically to the parameters to submit with the request " +
                "(deprecated, use hx-vals; removed in htmx 4: use hx-vals with the js: prefix)",
        "hx-ws" to "Legacy htmx 1 attribute, replaced by the WebSockets extension " +
                "(ws-connect/ws-send in htmx 2, hx-ws:connect/hx-ws:send in htmx 4)",
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
        return AttributeUtil.attributes.contains(name) || AttributeUtil.isStatusAttribute(name)
    }

    fun hasValue(): Boolean {
        return name != ""
    }

    @Suppress("ReturnCount")
    private fun buildTypeText(): String {
        val statusCode = AttributeUtil.statusCode(name)
        if (statusCode != null) {
            return buildStatusTypeText(statusCode)
        }
        return typeTexts.getOrDefault(name, "Htmx")
    }

    private fun buildStatusTypeText(statusCode: String): String {
        val matches = when {
            statusCode.endsWith("xx") -> "any ${statusCode.first()}xx response"
            statusCode.endsWith("x") -> "responses ${statusCode.dropLast(1)}0-${statusCode.dropLast(1)}9"
            else -> "a $statusCode response"
        }
        return "Handles $matches with key:value settings, e.g. swap:none target:#errors (htmx 4)"
    }
}
