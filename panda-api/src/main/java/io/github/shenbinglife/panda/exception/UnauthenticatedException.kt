package io.github.shenbinglife.panda.exception

import java.lang.RuntimeException

class UnauthenticatedException(message: String, cause: Throwable?) : RuntimeException(message, cause) {
    constructor(msg: String) : this(msg, null)
}