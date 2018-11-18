package io.github.shenbinglife.panda.entity

import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Table

/**
 * 类名
 *
 * @author shenbing
 * @version 2018/11/16
 * @since since
 */
@Entity
@Table(name = "sys_config")
class SystemConfig : BaseEntity() {
    enum class ConfigType {
        number, date, datetime, string
    }

    var name: String? = null
    var value: String? = null
    var description: String? = null
    @Enumerated(EnumType.STRING)
    var type: ConfigType? = null
}
