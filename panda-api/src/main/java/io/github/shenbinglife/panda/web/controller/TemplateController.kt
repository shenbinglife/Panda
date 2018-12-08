package io.github.shenbinglife.panda.web.controller

import io.github.shenbinglife.panda.domain.TemplateModel
import io.github.shenbinglife.panda.service.TemplateService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * 类名
 *
 * @author shenbing
 * @version 2018/11/30
 * @since since
 */
@RestController
class TemplateController {

    @Autowired
    lateinit var templateService: TemplateService

    @PostMapping("template")
    fun create(@RequestBody template: TemplateModel) {
        templateService.build(template)
    }

    @GetMapping("database/tables")
    fun getTables() :List<String>{
        return templateService.getTables()
    }
}
