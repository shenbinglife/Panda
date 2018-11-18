package io.github.shenbinglife.panda.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonSetter
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "sys_user")
class User : BaseEntity() {
    var name: String? = null

    @set:JsonSetter
    @get:JsonIgnore
    var password: String? = null

    var age: Short? = null

    var mobilePhone: String? = null


}