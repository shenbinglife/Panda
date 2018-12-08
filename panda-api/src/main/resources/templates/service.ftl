package io.github.shenbinglife.panda.service

import io.github.shenbinglife.panda.dao.${className}Repository
import io.github.shenbinglife.panda.entity.${className}
import io.github.shenbinglife.panda.utils.BaseService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ${className}Service : BaseService<${className}, ${className}Repository>() {

}