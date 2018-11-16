package io.github.shenbinglife.panda.utils

import org.slf4j.LoggerFactory
import org.springframework.context.MessageSource
import org.springframework.context.MessageSourceAware
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

@Component
class LocaleMessageBuilder : MessageSourceAware {

    var source: MessageSource? = null
    val converter = ExceptionConverter()

    fun build(e: Exception): String {
        assert(source != null) { "MessageSource has not been initialized" }
        val code = converter.convert(e.javaClass)
        val servletRequestAttributes = RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes
        val locale = servletRequestAttributes.request.locale
        return source!!.getMessage(code, null, locale)
    }

    fun build(code: String): String {
        assert(source != null) { "MessageSource has not been initialized" }
        val servletRequestAttributes = RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes
        val locale = servletRequestAttributes.request.locale
        return source!!.getMessage(code, null, locale)
    }

    override fun setMessageSource(source: MessageSource) {
        this.source = source
    }
}

class ExceptionConverter {
    companion object {
        val LOGGER = LoggerFactory.getLogger(ExceptionConverter.javaClass)
    }

    var defaultCode = "default.message"
    var mappings = HashMap<Class<Exception>, String>()

    fun add(e: Class<Exception>, code: String) {
        val old = mappings.put(e, code)
        if (old != null && old != code) {
            LOGGER.warn("replace the ${e.javaClass.name} error code mapping with [$code], old is [$old]")
        }
    }

    fun addAll(map: Map<Class<Exception>, String>) {
        mappings.putAll(map)
    }

    fun convert(clazz: Class<Exception>): String {
        return mappings.getOrDefault(clazz, defaultCode)
    }
}