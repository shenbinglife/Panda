package io.github.shenbinglife.panda.utils

import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import javax.servlet.http.HttpServletRequest

class Message<T>(msg: String, code: String) {
    var code: String = code
    var msg: String = msg
    var data: T? = null
    var requestId: String? = null
    var uri: String? = null

    init {
        val requestAttributes = RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes
        val req = requestAttributes.request as HttpServletRequest
        requestId = req.getAttribute("Request-Id") as String?
        uri = req.requestURI
    }

    constructor(msg: String, code: String, data: T) : this(msg, code) {
        this.data = data
    }


    constructor(msg: String, code: Int) : this(msg, code.toString())
}