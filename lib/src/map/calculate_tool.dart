import 'dart:convert';

import 'package:flutter/services.dart';
import 'package:meta/meta.dart';

import '../map/model/latlng.dart';

class CalculateTools {
  static const _channel = MethodChannel('me.yohom/tool');

  static CalculateTools _instance;

  CalculateTools._();

  factory CalculateTools() {
    if (_instance == null) {
      _instance = CalculateTools._();
      return _instance;
    } else {
      return _instance;
    }
  }

  /// 转换坐标系
  ///
  /// [lat] 纬度
  /// [lon] 经度
  ///
  /// [type] 原坐标类型, 这部分请查阅高德地图官方文档
  Future<LatLng> convertCoordinate({
    @required double lat,
    @required double lon,
    @required LatLngType type,
  }) async {
    int intType = LatLngType.values.indexOf(type);

    String result = await _channel.invokeMethod(
      'tool#convertCoordinate',
      {
        'lat': lat,
        'lon': lon,
        'type': intType,
      },
    );

    if (result == null) {
      return null;
    }

    return LatLng.fromJson(jsonDecode(result));
  }

  static const _searchChannel = MethodChannel('me.yohom/search');

  /// 距离测量 参考[链接](https://lbs.amap.com/api/android-sdk/guide/computing-equipment/distancesearch)
  ///
  /// type 分别对应
  Future<List<int>> distanceSearch(
      List<LatLng> origins, LatLng target, DistanceSearchType type) async {
    List<Map<String, Object>> oriList = [];

    origins.forEach((o) {
      oriList.add(o.toJson());
    });

    Map<String, dynamic> params = {
      "origin": oriList,
      "target": target.toJson(),
      "type": DistanceSearchType.values.indexOf(type),
    };

    List<dynamic> result =
        await _searchChannel.invokeMethod("tool#distanceSearch", params);
    return result.map((v) => v as int).toList();
  }
}

enum LatLngType {
  gps,
  baidu,
  mapBar,
  mapABC,
  soSoMap,
  aliYun,
  google,
}

enum DistanceSearchType {
  line,
  driver,
}
