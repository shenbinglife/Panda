package io.github.shenbinglife.panda.service

import io.github.shenbinglife.panda.dao.UserRepository
import io.github.shenbinglife.panda.entity.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Service

@Service
class UserService : BaseService<User, UserRepository>() {

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

    fun getByPage(page: Int, size: Int, name: String?): Page<User>? {
        val pageRequest = PageRequest.of(page - 1, size, Sort.by("createTime").descending())
        val userDao = baseDao as JpaSpecificationExecutor<User>
        return userDao.findAll({ root, query, builder ->
            if (name != null && name.isNotBlank()) {
                query.where(builder.like(root.get("name"), "%$name%"))
            }
            query.restriction
        }, pageRequest)
    }

}