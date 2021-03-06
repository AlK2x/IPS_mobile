package ips.mobile.gitrockstars.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ips.mobile.gitrockstars.data.QueryRepository
import ips.mobile.gitrockstars.data.GitStarsRepository
import javax.inject.Inject

class SearchViewModelFactory @Inject constructor(
    private val gitStarsRepository: GitStarsRepository,
    private val queryRepository: QueryRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            try {
                @Suppress("UNCHECKED_CAST")
                return SearchViewModel(gitStarsRepository, queryRepository) as T
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}