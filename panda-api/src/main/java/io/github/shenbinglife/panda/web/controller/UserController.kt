package io.github.shenbinglife.panda.web.controller

import io.github.shenbinglife.panda.entity.User
import io.github.shenbinglife.panda.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.validation.Validator
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.lang.RuntimeException
import java.util.*
import javax.validation.constraints.Min
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.InitBinder



@Validated
@RestController
@RequestMapping("/users")
class UserController {
    @Autowired
    lateinit var userService: UserService

    /**
     * get users by page
     */
    @Validated
    @GetMapping
    fun getUsers(@RequestParam(defaultValue = "1") page: Int,
                 @RequestParam(defaultValue = "10") pageSize: Int,
                 name: String?): Page<User> {
        return userService.getByPage(page, pageSize, name)
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