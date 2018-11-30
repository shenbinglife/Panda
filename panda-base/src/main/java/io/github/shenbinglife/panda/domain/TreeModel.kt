package io.github.shenbinglife.panda.domain

class TreeModel(var id: Any?) {
    var name: String? = null
    var code: String? = null
    var icon: String? = null
    var url: String? = null
    var properties = HashMap<String, Any>()
    var children = ArrayList<TreeModel>()

    fun addPropertiy(key: String, value: Any) {
        properties.put(key, value)
    }

}
