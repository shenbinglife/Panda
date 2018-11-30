package io.github.shenbinglife.panda.web.controller

import io.github.shenbinglife.panda.entity.Role
import io.github.shenbinglife.panda.service.RoleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("roles")
class RoleController {

    @Autowired
    lateinit var roleService: RoleService

    @GetMapping
    fun getRoles(@RequestParam(defaultValue = "1") page: Int,
                 @RequestParam(defaultValue = "10") pageSize: Int,
                 name: String?): Page<Role> {
        return roleService.getByPage(page, pageSize, name)
    }

    @PutMapping
    fun update(@RequestBody role: Role): Role {
        return roleService.update(role)
    }

    @DeleteMapping
    fun delete(@RequestParam("ids") ids: ArrayList<Long?>) {
        val idsNotNull = ids.filterNotNull()
        roleService.delete(idsNotNull)
    }

}