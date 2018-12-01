package io.github.shenbinglife.panda.web.controller

import io.github.shenbinglife.panda.domain.TemplateModel
import io.github.shenbinglife.panda.service.TemplateService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * 类名
 *
 * @author shenbing
 * @version 2018/11/30
 * @since since
 */
@RestController
@RequestMapping("templates")
class TemplateController {

    @Autowired
    lateinit var templateService: TemplateService

    @PostMapping
    fun create(@RequestBody template: TemplateModel) {
        templateService.build(template)
    }
}
