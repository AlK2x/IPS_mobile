package ips.mobile.gitrockstars.data

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ips.mobile.gitrockstars.model.SearchResult
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GitStarsRepository @Inject constructor(val dataSource: GitStarsDataSource) {

    companion object {
        const val LIMIT = 10
    }

    fun find(query: String, page: Int): Single<SearchResult> = dataSource
        .find(query, page, LIMIT)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

}