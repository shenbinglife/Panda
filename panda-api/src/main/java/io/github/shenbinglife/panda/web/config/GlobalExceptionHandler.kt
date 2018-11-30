package io.github.shenbinglife.panda.web.config

import io.github.shenbinglife.common.base.exception.impl.InvalidParamsException
import io.github.shenbinglife.common.base.exception.impl.PermissionForbiddenException
import io.github.shenbinglife.common.base.exception.impl.UnauthenticatedException
import io.github.shenbinglife.panda.utils.LocaleMessageBuilder
import io.github.shenbinglife.panda.utils.Message
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@ControllerAdvice
class GlobalExceptionHandler {

    companion object {
        val LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)!!
    }

    @Autowired
    lateinit var builder: LocaleMessageBuilder

    @ResponseBody
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidParamsException::class)
    fun handleInvalidParamsException(req: HttpServletRequest,
                                     res: HttpServletResponse,
                                     e: InvalidParamsException): Message<Void> {
        LOGGER.error("HTTP Request Failed, invalid parameters, URL:${req.requestURL}, Method: ${req.method}", e)
        return builder.buildAsMessage(e)
    }

    @ResponseBody
    @ResponseStatus(code = HttpStatus.FORBIDDEN)
    @ExceptionHandler(PermissionForbiddenException::class)
    fun handlePermissionException(req: HttpServletRequest,
                                  res: HttpServletResponse,
                                  e: PermissionForbiddenException): Message<Void> {
        LOGGER.error("HTTP Request Failed, permission forbidden, URL:${req.requestURL}, Method: ${req.method}", e)
        return builder.buildAsMessage(e)
    }

    @ResponseBody
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthenticatedException::class)
    fun handleAuthException(req: HttpServletRequest,
                            res: HttpServletResponse,
                            e: UnauthenticatedException): Message<Void> {
        LOGGER.error("HTTP Request Failed, auth failed, URL:${req.requestURL}, Method: ${req.method}", e)
        return builder.buildAsMessage(e)
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception::class)
    fun handleException(req: HttpServletRequest,
                        res: HttpServletResponse,
                        e: Exception): Message<Void> {
        LOGGER.error("HTTP Request Error, URL:${req.requestURL}, Method: ${req.method}", e)
        return builder.buildAsMessage(e)
    }

}

