package me.yohom.amapbasesearch

import me.yohom.amapbasesearch.search.*

/**
 * 搜索功能集合
 */
val SEARCH_METHOD_HANDLER: Map<String, SearchMethodHandler> = mapOf(
        "search#calculateDriveRoute" to CalculateDriveRoute,
        "search#calculateWalkRoute" to CalculateWalkRoute,
        "search#searchPoi" to SearchPoiKeyword,
        "search#searchPoiBound" to SearchPoiBound,
        "search#searchPoiPolygon" to SearchPoiPolygon,
        "search#searchPoiId" to SearchPoiId,
        "search#searchRoutePoiLine" to SearchRoutePoiLine,
        "search#searchRoutePoiPolygon" to SearchRoutePoiPolygon,
        "search#searchGeocode" to SearchGeocode,
        "search#searchReGeocode" to SearchReGeocode,
        "search#searchBusStation" to SearchBusStation,
        "tool#distanceSearch" to DistanceSearchHandler
)
