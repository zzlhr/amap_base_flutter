import 'dart:convert';
import 'dart:ui';

import 'package:amap_base_map/amap_base_map.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';

class PolygonOptions {
  final List<LatLng> points;
  final double strokeWidth;
  final Color strokeColor;
  final Color fillColor;
  final int zIndex;
  final bool visible;
  final List<BaseHoleOptions> holeOptions;

  PolygonOptions({
    @required this.points,
    this.strokeWidth = 1,
    this.strokeColor = Colors.black,
    this.fillColor = Colors.black,
    this.zIndex = 0,
    this.visible = true,
    this.holeOptions = const [],
  });

  Map<String, dynamic> toJson() {
    return {
      'points': points,
      'strokeWidth': strokeWidth,
      'strokeColor': strokeColor.value.toRadixString(16),
      'fillColor': fillColor.value.toRadixString(16),
      'zIndex': zIndex,
      'visible': visible,
      'holeOptions': holeOptions
    };
  }

  String toJsonString() => jsonEncode(toJson());

  @override
  String toString() {
    return 'PolygonOptions{points: $points, strokeWidth: $strokeWidth, strokeColor: $strokeColor, fillColor: $fillColor, zIndex: $zIndex, visible: $visible, holeOptions: $holeOptions}';
  }
}

class BaseHoleOptions {}
