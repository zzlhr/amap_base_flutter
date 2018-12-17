package me.yohom.amapbase.common

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.TypeReference

inline fun <reified K> String.parseJson(): K {
    return JSON.parseObject(this, object : TypeReference<K>() {}.type)
}

fun Any.toJson(): String {
    return JSON.toJSONString(this)
}