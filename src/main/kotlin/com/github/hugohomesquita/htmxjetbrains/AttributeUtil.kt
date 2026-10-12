package com.github.hugohomesquita.htmxjetbrains

object AttributeUtil {
    val attributes = arrayOf(
        // Core attributes (htmx 2 and htmx 4)
        "hx-boost",
        "hx-get",
        "hx-post",
        "hx-put",
        "hx-patch",
        "hx-delete",
        "hx-on",
        "hx-push-url",
        "hx-replace-url",
        "hx-select",
        "hx-select-oob",
        "hx-swap",
        "hx-swap-oob",
        "hx-target",
        "hx-trigger",
        "hx-vals",
        "hx-confirm",
        "hx-disable",
        "hx-encoding",
        "hx-headers",
        "hx-history-elt",
        "hx-include",
        "hx-indicator",
        "hx-preserve",
        "hx-sync",
        "hx-validate",
        // Added in htmx 4
        "hx-query",
        "hx-action",
        "hx-method",
        "hx-config",
        "hx-ignore",
        "hx-status",
        "hx-morph-skip",
        "hx-morph-skip-children",
        "hx-preload",
        "hx-pending",
        // htmx 2 only (removed, renamed or moved to extensions in htmx 4)
        "hx-disabled-elt",
        "hx-disinherit",
        "hx-inherit",
        "hx-ext",
        "hx-history",
        "hx-params",
        "hx-prompt",
        "hx-request",
        "hx-sse",
        "hx-vars",
        "hx-ws",
    )

    val statusAttributes = arrayOf(
        "hx-status:4xx",
        "hx-status:5xx",
        "hx-status:404",
        "hx-status:500",
    )

    private val statusAttributePattern = Regex("hx-status:(\\d{3}|\\d{2}x|\\dxx)")

    fun isStatusAttribute(name: String): Boolean {
        return statusAttributePattern.matches(name)
    }

    fun statusCode(name: String): String? {
        return statusAttributePattern.matchEntire(name)?.groupValues?.get(1)
    }
}
