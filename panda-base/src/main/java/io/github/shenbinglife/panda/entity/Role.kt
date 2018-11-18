package io.github.shenbinglife.panda.entity

import javax.persistence.*

/**
 * 类名
 *
 * @author shenbing
 * @version 2018/11/16
 * @since since
 */
@Entity
@Table(name = "sys_role")
class Role : BaseEntity() {
    var name: String? = null
    var description: String? = null

    @ManyToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinTable(name = "sys_user_role_relation",
            joinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")],
            inverseJoinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")])
    var users: List<User> = ArrayList()

    @ManyToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinTable(name = "sys_menu_role_relation",
            joinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")],
            inverseJoinColumns = [JoinColumn(name = "menu_id", referencedColumnName = "id")])
    var menus: List<Menu> = ArrayList()
}
