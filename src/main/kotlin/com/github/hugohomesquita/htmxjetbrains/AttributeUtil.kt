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
        "hx-status:2xx",
        "hx-status:200",
        "hx-status:201",
        "hx-status:4xx",
        "hx-status:400",
        "hx-status:401",
        "hx-status:403",
        "hx-status:404",
        "hx-status:409",
        "hx-status:422",
        "hx-status:429",
        "hx-status:5xx",
        "hx-status:50x",
        "hx-status:500",
        "hx-status:502",
        "hx-status:503",
    )

    const val STATUS_ATTRIBUTE_REGEX = "(?i)hx-status:([1-5](?:\\d{2}|\\dx|xx))(?::inherited)?(?::append)?"

    private val statusAttributePattern = Regex(STATUS_ATTRIBUTE_REGEX)

    fun isStatusAttribute(name: String): Boolean {
        return statusCode(name) != null
    }

    fun statusCode(name: String): String? {
        if (!name.startsWith("hx-status:", ignoreCase = true)) {
            return null
        }
        return statusAttributePattern.matchEntire(name)?.groupValues?.get(1)?.lowercase()
    }
}
