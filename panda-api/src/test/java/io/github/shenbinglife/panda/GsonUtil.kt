package io.github.shenbinglife.panda

import com.google.gson.Gson

object GsonUtil {
    val gson = Gson()

    fun toJson(any : Any?):String {
        return gson.toJson(any)
    }

    fun <T> fromJson(json: String, clazz: Class<T>):T {
        return gson.fromJson(json, clazz)
    }
}