package ips.mobile.gitrockstars.model

import com.squareup.moshi.Json

data class SearchResult(
    @Json(name = "html_url") val totalCount: Int,
    @Json(name = "incomplete_results") val results: Boolean,
    @Json(name = "items") val items: List<User>
)