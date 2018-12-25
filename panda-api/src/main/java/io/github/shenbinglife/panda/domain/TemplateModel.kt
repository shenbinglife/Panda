package io.github.shenbinglife.panda.domain

/**
 * 类名
 *
 * @author shenbing
 * @version 2018/11/30
 * @since since
 */
class TemplateModel {
    lateinit var table: String
    var tablePrefix: String? = null

    var generateBackEnd = true
    var generateVue : Boolean = true
    var generateMenu: Boolean = true

}

