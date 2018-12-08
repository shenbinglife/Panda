package io.github.shenbinglife.panda.service

import io.github.shenbinglife.panda.dao.MenuRepository
import io.github.shenbinglife.panda.domain.TreeModel
import io.github.shenbinglife.panda.entity.Menu
import io.github.shenbinglife.panda.utils.BaseService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MenuService : BaseService<Menu, MenuRepository>() {

    fun getChildrenByRole(id: Long, roleId: Long): List<Menu> {
        return baseDao.getChildrenByRole(id, roleId)
    }

    fun getChildren(id: Long): List<Menu> {
        return baseDao.getByParentIdOrderBySort(id)
    }

    fun getMenuTree(parentId: Long, loadChild: Boolean): List<TreeModel> {
        val children = getChildren(parentId)
        return children.map {
            val treeModel = TreeModel(it.id)
            treeModel.code= it.code
            treeModel.name = it.name
            treeModel.icon = it.icon
            treeModel.url = it.url
            treeModel.children.addAll(getMenuTree(it.id!!, loadChild))
            treeModel
        }
    }

    fun getByCode(code: String):Menu? {
        return baseDao.getByCode(code)
    }

}