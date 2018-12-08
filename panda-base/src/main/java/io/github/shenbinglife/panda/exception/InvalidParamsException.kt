package io.github.shenbinglife.panda.exception

import java.lang.RuntimeException

class InvalidParamsException(message: String, cause: Throwable?) : RuntimeException(message, cause) {
    constructor(msg: String) : this(msg, null)
}