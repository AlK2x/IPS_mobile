package ru.iandreyshev.androidarchitecturecomponentsapp.viewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import ru.iandreyshev.model.player.IPlayerPresenter
import ru.iandreyshev.model.player.PlayingState
import ru.iandreyshev.model.player.Timeline
import ru.iandreyshev.model.playlist.IPlaylistPresenter
import ru.iandreyshev.model.playlist.ITrack

class PlaylistViewModel : ViewModel(), IPlaylistPresenter, IPlayerPresenter {

    val trackTitle = MutableLiveData<String>()
    val trackTimeline = MutableLiveData<Timeline>()
    val trackPlayingState = MutableLiveData<PlayingState>()
    val playlist = MutableLiveData<List<ITrack>>()

    override fun updateTitle(title: String?) {
        trackTitle.value = title
    }

    override fun updateTimeline(timeline: Timeline) {
        trackTimeline.value = timeline
    }

    override fun updatePlaying(state: PlayingState) {
        trackPlayingState.value = state
    }

    override fun updatePlaylist(playlist: List<ITrack>) {
        this.playlist.value = playlist
    }
}