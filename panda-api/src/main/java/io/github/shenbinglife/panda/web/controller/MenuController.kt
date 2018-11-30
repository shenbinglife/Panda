package io.github.shenbinglife.panda.web.controller

import io.github.shenbinglife.panda.domain.TreeModel
import io.github.shenbinglife.panda.entity.Menu
import io.github.shenbinglife.panda.service.MenuService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("menus")
class MenuController {
    @Autowired
    lateinit var menuService: MenuService

    @GetMapping("tree")
    fun getMenuTree(@RequestParam(defaultValue = "-1") parentId: Long = -1,
                    @RequestParam(defaultValue = "true") loadChild:Boolean = true): List<TreeModel> {
        return menuService.getMenuTree(parentId, loadChild)
    }

    @GetMapping("{id}/children")
    fun getChildrenByRole(@PathVariable("id") id: Long): List<Menu> {
        return menuService.getChildren(id)
    }

    @PutMapping
    fun update(@RequestBody menu: Menu): Menu {
        return menuService.update(menu)
    }

    @DeleteMapping
    fun delete(@RequestParam("ids") ids: List<Long?>) {
        val idsNotNull = ids.filterNotNull()
        menuService.delete(idsNotNull)
    }
}
