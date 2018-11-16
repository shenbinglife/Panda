package io.github.shenbinglife.panda.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSetter
import javax.persistence.Entity

@Entity
class User : BaseEntity() {
    var name: String? = null

    @set:JsonSetter
    @get:JsonIgnore
    var password: String? = null

    var age: Short? = null

    var mobilePhone: String? = null


}