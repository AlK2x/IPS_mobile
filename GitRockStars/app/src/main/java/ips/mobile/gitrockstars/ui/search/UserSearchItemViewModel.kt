package ips.mobile.gitrockstars.ui.search

import androidx.databinding.ObservableField
import ips.mobile.gitrockstars.model.User

class UserSearchItemViewModel(val user: User) {

    val title: ObservableField<String> = ObservableField()
    val description: ObservableField<String> = ObservableField()
    val avatar: ObservableField<String> = ObservableField()
    val language: ObservableField<String> = ObservableField()
    val url: ObservableField<String> = ObservableField()
    val stars: ObservableField<String> = ObservableField()

    init {
        title.set(user.name)
        description.set(user.description)
        avatar.set(user.owner.avatar_url)
        language.set(user.language)
        url.set(user.owner.url)
        stars.set(user.starts.toString())
    }

}