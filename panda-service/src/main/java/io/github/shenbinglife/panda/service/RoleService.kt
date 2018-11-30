package io.github.shenbinglife.panda.service

import io.github.shenbinglife.panda.dao.RoleRepository
import io.github.shenbinglife.panda.entity.Role
import io.github.shenbinglife.panda.utils.BaseService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class RoleService : BaseService<Role, RoleRepository>() {


}