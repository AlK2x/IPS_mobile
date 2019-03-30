package ru.iandreyshev.mymusicapplication.application

import android.app.Application
import ru.iandreyshev.model.player.Player
import ru.iandreyshev.model.playlist.Playlist
import ru.iandreyshev.model.repository.Repository
import ru.iandreyshev.mymusicapplication.presenter.PlayerPresenter
import ru.iandreyshev.mymusicapplication.presenter.PlaylistPresenter

class MusicApplication : Application() {
    private val mRepository = Repository()
    private val mPlaylist = Playlist(mRepository.getAllSongs(), ::onSelectSong)
    private val mPlayer = Player(this)
    private lateinit var mPlayerPresenter: PlayerPresenter

    override fun onCreate() {
        super.onCreate()

        instance = this

        mPlayerPresenter = PlayerPresenter(instance.resources, instance.mPlayer)
        instance.mPlayer.subscribe(mPlayerPresenter)
    }

    private fun onSelectSong(songId: Long) {
        val songToPlay = mRepository.getSongById(songId) ?: return
        mPlayer.setSong(songToPlay)
        mPlayer.onPlay()
    }

    companion object {
        private lateinit var instance: MusicApplication

        fun getPlaylistPresenter(): PlaylistPresenter {
            val playlistPresenter = PlaylistPresenter()
            instance.mPlaylist.subscribe(playlistPresenter)
            return playlistPresenter
        }

        fun getPlayerPresenter(): PlayerPresenter {
            return instance.mPlayerPresenter
        }
    }

}