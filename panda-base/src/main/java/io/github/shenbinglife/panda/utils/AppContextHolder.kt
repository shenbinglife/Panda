package io.github.shenbinglife.panda.utils

import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Component

/**
 * 类名
 *
 * @author shenbing
 * @version 2018/11/16
 * @since since
 */
@Component
class AppContextHolder : ApplicationContextAware {
    companion object {
        lateinit var app : ApplicationContext
    }

    override fun setApplicationContext(p0: ApplicationContext) {
        app = p0
    }
}
