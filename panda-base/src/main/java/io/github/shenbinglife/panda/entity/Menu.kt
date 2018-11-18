package io.github.shenbinglife.panda.entity

import javax.persistence.Entity
import javax.persistence.Table

/**
 * 菜单
 *
 * @author shenbing
 * @version 2018/11/16
 * @since since
 */
@Entity
@Table(name = "sys_menu")
class Menu : BaseEntity() {
    var parentId: Long? = null
    var name: String? = null
    var description: String? = null
    var url: String? = null
    var sort: Int? = null
    var icon: String? = null
}
