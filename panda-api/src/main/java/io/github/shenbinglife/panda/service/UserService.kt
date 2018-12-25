package io.github.shenbinglife.panda.service

import io.github.shenbinglife.panda.dao.UserRepository
import io.github.shenbinglife.panda.entity.User
import io.github.shenbinglife.panda.utils.BaseService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserService : BaseService<User, UserRepository>() {
    fun authUser(account: String, password: String) {
        baseDao.findByAccountAndPassword(account, password)
    }
}

