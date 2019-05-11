package me.yohom.amapbasesearch

import android.Manifest
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.PluginRegistry.Registrar

class AMapBaseSearchPlugin {
    companion object {
        lateinit var registrar: Registrar

        @JvmStatic
        fun registerWith(registrar: Registrar) {
            // 由于registrar用到的地方比较多, 这里直接放到全局变量里去好了
            AMapBaseSearchPlugin.registrar = registrar

            // 设置权限 channel
            MethodChannel(registrar.messenger(), "me.yohom/permission")
                    .setMethodCallHandler { methodCall, result ->
                        when (methodCall.method) {
                            "requestPermission" -> {
                                ActivityCompat.requestPermissions(
                                        registrar.activity(),
                                        arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION,
                                                Manifest.permission.ACCESS_FINE_LOCATION,
                                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                                Manifest.permission.READ_PHONE_STATE),
                                        321
                                )
                                registrar.addRequestPermissionsResultListener { requestCode, permissions, grantResults ->
                                    if (requestCode == 321) {
                                        result.success(grantResults[0] == PackageManager.PERMISSION_GRANTED)
                                    }
                                    return@addRequestPermissionsResultListener true
                                }
                            }
                            else -> result.notImplemented()
                        }
                    }

            // 设置key channel
            MethodChannel(registrar.messenger(), "me.yohom/amap_base")
                    .setMethodCallHandler { methodCall, result ->
                        when (methodCall.method) {
                            "setKey" -> result.success("android端需要在Manifest里配置key")
                            else -> result.notImplemented()
                        }
                    }

            // 搜索 channel
            MethodChannel(registrar.messenger(), "me.yohom/search")
                    .setMethodCallHandler { call, result ->
                        SEARCH_METHOD_HANDLER[call.method]
                                ?.onMethodCall(call, result) ?: result.notImplemented()
                    }
        }
    }
}
