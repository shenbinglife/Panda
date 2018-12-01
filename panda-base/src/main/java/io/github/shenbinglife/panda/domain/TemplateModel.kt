package io.github.shenbinglife.panda.domain

/**
 * 类名
 *
 * @author shenbing
 * @version 2018/11/30
 * @since since
 */
class TemplateModel() {
    var table: String? = null
    var tableName: String? = null
    var tablePrefix: String? = null
    lateinit var className: String
    lateinit var fieldNames: List<String>
    var generateBackEnd = true
    var generateVue : Boolean = true
    var generateMenu: Boolean = true
}
