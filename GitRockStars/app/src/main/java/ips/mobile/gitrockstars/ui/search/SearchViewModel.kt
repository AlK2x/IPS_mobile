package ips.mobile.gitrockstars.ui.search

import androidx.lifecycle.*
import io.reactivex.SingleSource
import ips.mobile.gitrockstars.data.QueryRepository
import ips.mobile.gitrockstars.data.GitStarsRepository
import ips.mobile.gitrockstars.model.User

class SearchViewModel constructor(
    private val gitStarsRepository: GitStarsRepository,
    private val queryRepository: QueryRepository
) : ViewModel() {

    private var page = 1

    private val _progress = MutableLiveData<Boolean>()
    val progress: LiveData<Boolean>
        get() = _progress

    private val _query = MutableLiveData<String>()
    val query: LiveData<String>
        get() = _query

    private val _users = MutableLiveData<List<UserSearchItemViewModel>>()
    val users: LiveData<List<UserSearchItemViewModel>> = Transformations
        .switchMap(_query) { query ->
            fetch(query, page)
        }

    private val _error = MutableLiveData<Boolean?>()
    val error: LiveData<Boolean?>
        get() = _error

    fun setQuery(query: String) {
        page = 1
        queryRepository.save(query)
        _users.value = emptyList()
        _query.value = query
    }

    fun fetchNextItems() {
        page++
        _query.value?.let { _query.value = it }
    }

    fun refreshItems() {
        queryRepository.clear()
        setQuery(queryRepository.find())
    }

    private fun fetch(query: String, page: Int): LiveData<List<UserSearchItemViewModel>> {
        _progress.value = true
        return gitStarsRepository
            .find(query, page)
            .map { result ->
                val current = _users.value ?: emptyList()
                val latest = toItems(result.items)
                val newValue = combine(current, latest)
                _users.value = newValue
                _progress.value = false
                newValue
            }
            .onErrorResumeNext{ it ->
                _error.value = true
                _progress.value = false
                SingleSource { }
            }
            .toFlowable()
            .toLiveData()
    }

    private fun <T> combine(current: List<T>, latest: List<T>): List<T> {
        return mutableListOf<T>().apply {
            addAll(current)
            addAll(latest)
        }
    }

    private fun toItems(repos: List<User>): List<UserSearchItemViewModel> {
        return repos.map { repo -> UserSearchItemViewModel(repo) }
    }

}