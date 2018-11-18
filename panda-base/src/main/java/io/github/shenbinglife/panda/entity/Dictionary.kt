package io.github.shenbinglife.panda.entity

import javax.persistence.Entity
import javax.persistence.Table

/**
 * 字典类
 *
 * @author shenbing
 * @version 2018/11/16
 * @since since
 */
@Entity
@Table(name = "sys_dictionary")
class Dictionary : BaseEntity() {
    var parentId: Long? = null
    var type: String? = null
    var name: String? = null
    var code: String? = null
    var value: String? = null
    var description: String? = null
}
