package io.github.shenbinglife.panda.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonSetter
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Table

@Entity
@Table(name = "sys_user")
class User : BaseEntity() {
    var account: String? = null
    var name: String? = null

    @set:JsonSetter
    @get:JsonIgnore
    var password: String? = null

    var age: Short? = null
    var mobilePhone: String? = null
    var email: String? = null
    var description: String? = null
    @Enumerated(EnumType.STRING)
    var state: UserState = UserState.DISABLED
}

enum class UserState {
    ENABLED, DISABLED
}