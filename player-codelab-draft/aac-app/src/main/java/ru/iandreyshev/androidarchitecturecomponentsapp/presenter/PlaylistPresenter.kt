package ru.iandreyshev.androidarchitecturecomponentsapp.presenter

import ru.iandreyshev.androidarchitecturecomponentsapp.viewModel.PlaylistViewModel
import ru.iandreyshev.model.playlist.IPlaylistPresenter
import ru.iandreyshev.model.playlist.ITrack

class PlaylistPresenter : IPlaylistPresenter {

    private var mViewModel: PlaylistViewModel? = null

    override fun updatePlaylist(playlist: List<ITrack>) {
        mViewModel?.updatePlaylist(playlist)
    }

}
