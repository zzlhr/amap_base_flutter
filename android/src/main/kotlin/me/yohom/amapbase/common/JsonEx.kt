package me.yohom.amapbase.common

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.TypeReference
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

// 关于为什么要同时使用Gson和FastJson
// Gson的解析是基于字段的, 而FastJson的解析是基于Getter Setter的. 而高德地图的sdk已经被混淆了, 字段都是abcd, 使用
// Gson无法解析/序列化, 当碰到要从Android返回复杂数据给dart端时, 需要手动再构造一个和高德SDK一样的类, 然后序列化传递给dart
// 这个过程是相当痛苦的, 经过了几轮的功能迭代后, 不堪其苦, 遂决定找个使用Getter Setter序列化的解析库, 而FastJson也有
// 一个问题, 就是目标类必须要有一个默认/无参构造器, 而高德SDK的某些类(比如CameraPosition)是没有无参构造器的. 造成有些类无法解析
// 最终决定在需要Getter Setter序列化的时候, 就采用FastJson, 其他情况采用Gson.

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