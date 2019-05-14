package me.yohom.amapbaselocation

import me.yohom.amapbaselocation.location.Init
import me.yohom.amapbaselocation.location.StartLocate
import me.yohom.amapbaselocation.location.StopLocate

/**
 * 定位功能集合
 */
val LOCATION_METHOD_HANDLER: Map<String, LocationMethodHandler> = mapOf(
        "location#init" to Init,
        "location#startLocate" to StartLocate,
        "location#stopLocate" to StopLocate
)
