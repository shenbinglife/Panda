package io.github.shenbinglife.panda.dao

import io.github.shenbinglife.panda.entity.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    fun findByName(name: String)
    fun findByAccountAndPassword(username: String, password: String)
}

@Repository
interface RoleRepository : JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> {
    fun findByName(name: String)
}

@Repository
interface MenuRepository : JpaRepository<Menu, Long>, JpaSpecificationExecutor<Menu> {
    fun findByName(name: String)

    @Query(value = "select m.* form sys_menu m inner join sys_menu_role_relation r on r.menu_id=m.id" +
            "where m.parent_id={id} and r.role_id={roleId}", nativeQuery = true)
    fun getChildrenByRole(@Param("id") id: Long, @Param("roleId") roleId: Long): List<Menu>

    fun getByParentIdOrderBySort(id: Long): List<Menu>

    fun getByCode(code: String) : Menu?
}

@Repository
interface SystemConfigRepository : JpaRepository<SystemConfig, Long>, JpaSpecificationExecutor<SystemConfig> {
    fun findByName(name: String)
}

@Repository
interface DictionaryRepository : JpaRepository<Dictionary, Long>, JpaSpecificationExecutor<Dictionary> {
    fun findByName(name: String)
}