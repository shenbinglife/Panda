package io.github.shenbinglife.panda.dao

import io.github.shenbinglife.panda.entity.${className}
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

@Repository
interface ${className}Repository : JpaRepository<${className}, Long>, JpaSpecificationExecutor<${className}> {

}