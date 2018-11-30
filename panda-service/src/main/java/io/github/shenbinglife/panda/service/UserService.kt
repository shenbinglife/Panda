package io.github.shenbinglife.panda.service

import io.github.shenbinglife.panda.dao.UserRepository
import io.github.shenbinglife.panda.entity.User
import io.github.shenbinglife.panda.utils.BaseService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserService : BaseService<User, UserRepository>() {
    fun authUser(user: AuthUser) {
        baseDao.findByAccountAndPassword(user.account, user.password)
    }

//    fun getByPage(page: Int, size: Int, name: String?): Page<User>? {
//        val pageRequest = PageRequest.of(page, size, Sort.by("createTime").descending())
//        return userRepo.findAll(object : Specification<User> {
//            override fun toPredicate(root: Root<User>, query: CriteriaQuery<*>, builder: CriteriaBuilder): Predicate {
//                if(name != null && name.isNotBlank()) {
//                    query.where(builder.like(root.get("name"), "%$name%"))
//                }
//                return query.restriction
//            }
//        }, pageRequest)
//    }
}

class AuthUser(var account: String, var password: String)