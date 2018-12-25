package io.github.shenbinglife.panda.utils

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.context.MessageSourceAware
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import javax.servlet.ServletRequest
import javax.validation.ConstraintViolationException

@Component
class LocaleMessageBuilder : MessageSourceAware {

    var source: MessageSource? = null

    @Autowired
    lateinit var converter: ExceptionConverter

    fun build(e: Exception): String {
        assert(source != null) { "MessageSource has not been initialized" }
        val code = converter.convert(e.javaClass)
        val servletRequestAttributes = RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes
        val locale = servletRequestAttributes.request.locale
        return source!!.getMessage(code, null, locale)
    }

    fun build(localeCode: String): String {
        assert(source != null) { "MessageSource has not been initialized" }
        val servletRequestAttributes = RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes
        val locale = servletRequestAttributes.request.locale
        return source!!.getMessage(localeCode, null, locale)
    }

    fun buildAsMessage(e: Exception): Message<Void> {
        assert(source != null) { "MessageSource has not been initialized" }
        return if (e is ConstraintViolationException) {
            Message(e.localizedMessage, e.message?: "xx")
        } else {
            val code = converter.convert(e.javaClass)
            val servletRequestAttributes = RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes
            val locale = servletRequestAttributes.request.locale
            val msg = source!!.getMessage(code, null, locale)
            Message(msg, code)
        }
    }

    fun getErrorCode(e: Exception): String {
        return converter.convert(e.javaClass)
    }

    override fun setMessageSource(source: MessageSource) {
        this.source = source
    }
}

@Component
class ExceptionConverter {
    companion object {
        val LOGGER = LoggerFactory.getLogger(ExceptionConverter::class.java)!!
    }

    var defaultCode = "default.message"
    var mappings = HashMap<Class<Exception>, String>()

    fun add(e: Class<Exception>, code: String) {
        val old = mappings.put(e, code)
        if (old != null && old != code) {
            LOGGER.warn("replace the ${e::class.qualifiedName} error code mapping with [$code], old is [$old]")
        }
    }

    fun addAll(map: Map<Class<Exception>, String>) {
        mappings.putAll(map)
    }

    fun convert(clazz: Class<Exception>): String {
        return mappings.getOrDefault(clazz, defaultCode)
    }
}