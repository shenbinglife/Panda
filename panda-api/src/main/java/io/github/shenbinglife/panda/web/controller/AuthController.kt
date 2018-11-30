package io.github.shenbinglife.panda.web.controller

import io.github.shenbinglife.panda.service.AuthUser
import io.github.shenbinglife.panda.service.UserService
import io.github.shenbinglife.panda.utils.Message
import io.github.shenbinglife.panda.utils.ResponseCode.LOGIN_SUCCESS
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("auth")
class AuthController {

    @Autowired
    lateinit var userService: UserService

    @PostMapping
    fun auth(@RequestBody user: AuthUser):Message<Void> {
        userService.authUser(user)
        return Message("user login success", LOGIN_SUCCESS)
    }
}
