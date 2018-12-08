package io.github.shenbinglife.panda.entity

import java.util.Date
import javax.persistence.Entity
import javax.persistence.Table

/**
 * <#if remarks?? || remarks == ''>Table: ${table}<#else>${remarks}</#if>
 *
 * @author shenbing
 * @version 2018/11/16
 * @since since
 */
@Entity
@Table(name = "${table}")
class ${className} : BaseEntity() {
  <#list columns as it>
    <#if it.fieldName != 'id' && it.fieldName != 'createTime' && it.fieldName != 'updateTime'>
      var ${it.fieldName}: ${it.fieldType}? = null
    </#if>
  </#list>
}
