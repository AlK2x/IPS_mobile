package ru.iandreyshev.androidarchitecturecomponentsapp.application

import android.app.Application
import ru.iandreyshev.androidarchitecturecomponentsapp.presenter.PlayerPresenter
import ru.iandreyshev.androidarchitecturecomponentsapp.presenter.PlaylistPresenter
import ru.iandreyshev.androidarchitecturecomponentsapp.viewModel.PlaylistViewModel
import ru.iandreyshev.model.player.IPlayerPresenter
import ru.iandreyshev.model.player.Player
import ru.iandreyshev.model.playlist.IPlaylistPresenter
import ru.iandreyshev.model.playlist.Playlist
import ru.iandreyshev.model.repository.Repository

class MusicApplication : Application() {
    private val mRepository = Repository()
    private val mPlaylist = Playlist(mRepository.getAllSongs(), ::onSelectSong)
    private val mPlayer = Player(this)
    private lateinit var mPlayerPresenter: PlayerPresenter

    override fun onCreate() {
        super.onCreate()

        instance = this
        mPlayerPresenter = PlayerPresenter(instance.mPlayer)
    }

    private fun onSelectSong(songId: Long) {
        val songToPlay = mRepository.getSongById(songId) ?: return
        mPlayer.setSong(songToPlay)
        mPlayer.onPlay()
    }

    companion object {
        private lateinit var instance: MusicApplication

        fun getPlaylistPresenter(viewModel: IPlaylistPresenter): PlaylistPresenter {
            val playlistPresenter = PlaylistPresenter()
            instance.mPlaylist.subscribe(viewModel)
            return playlistPresenter
        }

        fun getPlayerPresenter(viewModel: IPlayerPresenter): PlayerPresenter {
            instance.mPlayer.subscribe(viewModel)
            return instance.mPlayerPresenter
        }
    }

}