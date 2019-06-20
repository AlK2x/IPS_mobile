package ips.mobile.gitrockstars.api

import io.reactivex.Single
import ips.mobile.gitrockstars.model.SearchResult
import ips.mobile.gitrockstars.model.User
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubService {

    @GET("search/repositories")
    fun getRockStars(
        @Query("q") qualifiers: String,
        @Query("sort") sort: String,
        @Query("order") order: String,
        @Query("page") page: Int,
        @Query("per_page") limit: Int): Single<SearchResult>
}