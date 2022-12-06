package com.github.hugohomesquita.htmxjetbrains

import com.intellij.psi.impl.source.html.dtd.HtmlElementDescriptorImpl
import com.intellij.psi.xml.XmlTag
import com.intellij.xml.XmlAttributeDescriptor
import com.intellij.xml.XmlAttributeDescriptorsProvider

class AttributesProvider : XmlAttributeDescriptorsProvider {
    override fun getAttributeDescriptors(xmlTag: XmlTag): Array<XmlAttributeDescriptor> {
        return emptyArray()
    }

    @Suppress("ReturnCount")
    override fun getAttributeDescriptor(name: String, xmlTag: XmlTag): XmlAttributeDescriptor? {
        if (xmlTag.descriptor !is HtmlElementDescriptorImpl) return null
        val info = AttributeInfo(name)

        if (info.isHtmx()) {
            return HtmxAttributeDescriptor(name, xmlTag)
        }

        return null
    }
}
