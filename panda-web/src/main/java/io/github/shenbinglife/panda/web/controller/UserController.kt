package io.github.shenbinglife.panda.web.controller

import io.github.shenbinglife.common.base.exception.UncheckedException
import io.github.shenbinglife.panda.entity.User
import io.github.shenbinglife.panda.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/users")
class UserController: ApplicationContextAware {
    var appcation : ApplicationContext? = null



    @Autowired
    lateinit var userService: UserService

    override fun setApplicationContext(applicationContext: ApplicationContext) {
        appcation = applicationContext
    }
    /**
     * get users by page
     */
    @GetMapping
    fun getUsers(@RequestParam(defaultValue = "1") page: Int,
                 @RequestParam(defaultValue = "10") pageSize: Int,
                 name: String?): Page<User>? {
//        return userService.getByPage(page, pageSize, name)
        appcation!!.getMessage("message.default", null, Locale.CHINA);
        throw UncheckedException("xxx")
    }

    @PostMapping
    fun create(@RequestBody user: User): User {
        return userService.create(user)
    }

    @PutMapping
    fun update(@RequestBody user: User): User {
        return userService.update(user)
    }

    @DeleteMapping
    fun delete(@RequestParam("ids") ids: ArrayList<Long?>) {
        val idsNotNull = ids.filterNotNull()
        userService.delete(idsNotNull)
    }

}