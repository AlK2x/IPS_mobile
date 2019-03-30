package ru.iandreyshev.mymusicapplication.presenter

import ru.iandreyshev.model.playlist.IPlaylistPresenter
import ru.iandreyshev.model.playlist.ITrack

class PlaylistPresenter : IPlaylistPresenter {
    private var mPlaylist = listOf<ITrack>()

    private var mViewMap = mutableMapOf<IView, Boolean>()

    override fun updatePlaylist(playlist: List<ITrack>) {
        mPlaylist = playlist
        mViewMap.entries.forEach {
            if (it.value) {
                it.key.updatePlalist(mPlaylist)
            }
        }
    }

    fun onAttach(view: IView) {
        mViewMap[view] = true
        view.updatePlalist(mPlaylist)
    }

    fun onDetach(view: IView) {
        if (mViewMap.contains(view)) {
            mViewMap[view] = false
        }
    }

    fun onFinish(view: IView) {
        mViewMap.remove(view)
    }

    interface IView {
        fun updatePlalist(playlist: List<ITrack>)
    }
}