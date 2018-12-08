package io.github.shenbinglife.panda.exception


class BusinessException(message: String?, cause: Throwable?) : RuntimeException(message, cause) {
    constructor(msg: String) : this(msg, null)
}
