package me.yohom.amapbaselocation

import android.Manifest
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.PluginRegistry.Registrar

class AMapBaseLocationPlugin {
    companion object {

        lateinit var registrar: Registrar

        // 权限请求的相关变量
        private var permissionRequestCode = 0
        private var methodResult: MethodChannel.Result? = null

        @JvmStatic
        fun registerWith(registrar: Registrar) {
            // 由于registrar用到的地方比较多, 这里直接放到全局变量里去好了
            AMapBaseLocationPlugin.registrar = registrar

            // 设置权限 channel
            MethodChannel(registrar.messenger(), "me.yohom/permission")
                    .setMethodCallHandler { methodCall, result ->
                        when (methodCall.method) {
                            "requestPermission" -> {
                                permissionRequestCode = methodCall.hashCode()
                                methodResult = result

                                ActivityCompat.requestPermissions(
                                        registrar.activity(),
                                        arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION,
                                                Manifest.permission.ACCESS_FINE_LOCATION,
                                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                                Manifest.permission.READ_PHONE_STATE),
                                        permissionRequestCode
                                )
                                registrar.addRequestPermissionsResultListener { code, _, grantResults ->
                                    if (code == permissionRequestCode) {
                                        methodResult?.success(grantResults.all { it == PackageManager.PERMISSION_GRANTED })
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

            // 定位 channel
            MethodChannel(registrar.messenger(), "me.yohom/location")
                    .setMethodCallHandler { call, result ->
                        LOCATION_METHOD_HANDLER[call.method]
                                ?.onMethodCall(call, result) ?: result.notImplemented()
                    }
        }
    }
}
