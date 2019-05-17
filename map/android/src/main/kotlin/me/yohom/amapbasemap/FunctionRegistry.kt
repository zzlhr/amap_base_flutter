package me.yohom.amapbasemap

import me.yohom.amapbasemap.map.*

/**
 * 地图功能集合
 */
val MAP_METHOD_HANDLER: Map<String, MapMethodHandler> = mapOf(
        "map#setMyLocationStyle" to SetMyLocationStyle,
        "map#setUiSettings" to SetUiSettings,
        "marker#addMarker" to AddMarker,
        "marker#addMarkers" to AddMarkers,
        "marker#clear" to ClearMarker,
        "marker#removeMarkers" to RemoveMarkers,
        "map#showIndoorMap" to ShowIndoorMap,
        "map#setMapType" to SetMapType,
        "map#setLanguage" to SetLanguage,
        "map#clear" to ClearMap,
        "map#setZoomLevel" to SetZoomLevel,
        "map#setPosition" to SetPosition,
        "map#setMapStatusLimits" to SetMapStatusLimits,
        "tool#convertCoordinate" to ConvertCoordinate,
        "tool#convertCoordinates" to ConvertCoordinates,
        "tool#calcDistance" to CalcDistance,
        "offline#openOfflineManager" to OpenOfflineManager,
        "map#addPolyline" to AddPolyline,
        "map#removePolyline" to RemovePolyline,
        "map#removePolylines" to RemovePolylines,
        "map#zoomToSpan" to ZoomToSpan,
        "map#screenshot" to ScreenShot,
        "map#setCustomMapStylePath" to SetCustomMapStylePath,
        "map#setMapCustomEnable" to SetMapCustomEnable,
        "map#setCustomMapStyleID" to SetCustomMapStyleID,
        "map#getCenterPoint" to GetCenterLnglat,
        "map#showMyLocation" to ShowMyLocation,
        "map#addPolygon" to AddPolygon,
        "map#changeLatLng" to ChangeLatLng
)
