package ips.mobile.gitrockstars.model

import com.squareup.moshi.Json

data class User (
    val name: String = "",
    val full_name: String = "",
    val description: String = "",
    @Json(name = "html_url") val url: String = "",
    val owner: Owner = Owner(),
    val language: String = "",
    @Json(name = "stargazers_count") val starts: Int
)