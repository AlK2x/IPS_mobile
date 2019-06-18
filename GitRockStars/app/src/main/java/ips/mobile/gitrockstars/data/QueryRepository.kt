package ips.mobile.gitrockstars.data

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QueryRepository @Inject constructor(val dataSource: QueryDataSource) {

    fun find(): String = dataSource.find()

    fun save(q: String) = dataSource.update(q)

    fun clear() = dataSource.delete()

}