package io.github.shenbinglife.panda.web.config

import io.github.shenbinglife.common.base.domain.Message
import io.github.shenbinglife.common.base.util.JsonUtil
import io.github.shenbinglife.panda.utils.LocaleMessageBuilder
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.lang.Exception
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@ControllerAdvice
class GlobalExceptionHandler {

    companion object {
        val LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)!!
    }

    @Autowired
    lateinit var builder: LocaleMessageBuilder

    @ExceptionHandler
    fun handleException(req: HttpServletRequest, res: HttpServletResponse, e: Exception) {
        LOGGER.error("HTTP Request Error, URL:${req.requestURL}, Method: ${req.method}", e)
        val message = builder.build(e)
        val body = Message<Void>(message, 0)
        val json = JsonUtil.toJson(body)
        res.contentType = MediaType.APPLICATION_JSON_UTF8_VALUE
        res.writer.write(json)
    }
}