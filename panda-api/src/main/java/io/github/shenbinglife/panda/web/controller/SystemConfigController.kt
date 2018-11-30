package io.github.shenbinglife.panda.web.controller

import io.github.shenbinglife.panda.entity.SystemConfig
import io.github.shenbinglife.panda.service.SystemConfigService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("configs")
class SystemConfigController {

    @Autowired
    lateinit var systemConfigService: SystemConfigService

    @GetMapping
    fun getRoles(@RequestParam(defaultValue = "1") page: Int,
                 @RequestParam(defaultValue = "10") pageSize: Int,
                 name: String?): Page<SystemConfig> {
        return systemConfigService.getByPage(page, pageSize, name)
    }

    @PutMapping
    fun update(@RequestBody systemConfig: SystemConfig): SystemConfig {
        return systemConfigService.update(systemConfig)
    }

    @DeleteMapping
    fun delete(@RequestParam("ids") ids: ArrayList<Long?>) {
        val idsNotNull = ids.filterNotNull()
        systemConfigService.delete(idsNotNull)
    }
}

