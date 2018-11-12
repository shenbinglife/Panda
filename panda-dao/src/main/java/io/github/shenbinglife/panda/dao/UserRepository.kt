package io.github.shenbinglife.panda.dao

import io.github.shenbinglife.panda.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    fun findByName(name: String?)
}