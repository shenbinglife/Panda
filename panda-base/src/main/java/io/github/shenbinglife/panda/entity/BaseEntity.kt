package io.github.shenbinglife.panda.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.util.Date
import javax.persistence.*

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
open class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @CreatedDate
    var createTime: Date? = null

    @LastModifiedDate
    var updateTime: Date? = null
}