package ips.mobile.gitrockstars.ui.search

import androidx.databinding.ObservableField
import ips.mobile.gitrockstars.model.User

class UserSearchItemViewModel(val repo: User) {

    val title: ObservableField<String> = ObservableField()
    val description: ObservableField<String> = ObservableField()
    val avatar: ObservableField<String> = ObservableField()
    val language: ObservableField<String> = ObservableField()
    val url: ObservableField<String> = ObservableField()
    val stars: ObservableField<String> = ObservableField()

    init {
        title.set(repo.name)
        description.set(repo.description)
        avatar.set(repo.owner.avatar_url)
        language.set(repo.language)
        url.set(repo.url)
        stars.set(repo.starts.toString())
    }

}