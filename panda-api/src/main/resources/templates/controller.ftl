package io.github.shenbinglife.panda.web.controller

import io.github.shenbinglife.panda.entity.${className}
import io.github.shenbinglife.panda.service.${className}Service
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("${variableName}s")
class ${className}Controller {
  <#assign serviceName = variableName + 'Service'/>

  @Autowired
  lateinit var ${serviceName}: ${className}Service

  @GetMapping
  fun get${className}s(@RequestParam(defaultValue = "1") page: Int,
               @RequestParam(defaultValue = "10") pageSize: Int,
               name: String?): Page<${className}> {
    return ${serviceName}.getByPage(page, pageSize, name)
  }

  @PutMapping
  fun update(@RequestBody ${variableName}: ${className}): ${className} {
    return ${serviceName}.update(${variableName})
  }

  @DeleteMapping
  fun delete(@RequestParam("ids") ids: ArrayList<Long?>) {
  val idsNotNull = ids.filterNotNull()
    ${serviceName}.delete(idsNotNull)
  }

}