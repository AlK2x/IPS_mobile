package ips.mobile.gitrockstars.data

import io.reactivex.Single
import ips.mobile.gitrockstars.api.GitHubService
import ips.mobile.gitrockstars.model.SearchResult
import javax.inject.Inject

class TrendingReposDataSource @Inject constructor(val service: GitHubService) {

    companion object {
        const val SORT = "stars"
        const val ORDER = "desc"
    }

    fun find(query: String, page: Int, limit: Int): Single<SearchResult>
            = service.getTrending(query, SORT, ORDER, page, limit)

}