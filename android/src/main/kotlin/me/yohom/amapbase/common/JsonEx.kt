package me.yohom.amapbase.common

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.TypeReference
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

val gson: Gson = GsonBuilder().serializeNulls().create()

inline fun <reified K> String.parseJson(): K {
    return gson.fromJson(this, object : TypeToken<K>() {}.type)
}

fun Any.toJson(): String {
    return gson.toJson(this)
}

inline fun <reified K> String.parseFastJson(): K {
    return JSON.parseObject(this, object : TypeReference<K>() {}.type)
}

fun Any.toFastJson(): String {
    return JSON.toJSONString(this)
}