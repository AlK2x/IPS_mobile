package ru.iandreyshev.mymusicapplication.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_playlist.*
import kotlinx.android.synthetic.main.item_track.view.*
import ru.iandreyshev.model.player.PlayingState
import ru.iandreyshev.model.playlist.ITrack
import ru.iandreyshev.mymusicapplication.R
import ru.iandreyshev.mymusicapplication.application.MusicApplication
import ru.iandreyshev.mymusicapplication.presenter.PlayerPresenter
import ru.iandreyshev.mymusicapplication.presenter.PlaylistPresenter
import ru.iandreyshev.utils.disable
import ru.iandreyshev.utils.enable

class PlaylistActivity : AppCompatActivity(), PlaylistPresenter.IView, PlayerPresenter.IView {

    private val mPlaylistPresenter = MusicApplication.getPlaylistPresenter()
    private val mPlayerPresenter = MusicApplication.getPlayerPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playlist)

        initIntroView()
    }

    override fun onResume() {
        super.onResume()
        mPlaylistPresenter.onAttach(this)
        mPlayerPresenter.onAttach(this)
    }

    override fun onPause() {
        super.onPause()
        mPlaylistPresenter.onDetach(this)
        mPlayerPresenter.onDetach(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isFinishing) {
            mPlaylistPresenter.onFinish(this)
            mPlayerPresenter.onFinish(this)
        }
    }

    override fun updatePlalist(playlist: List<ITrack>) {
        tracksList.removeAllViews()

        playlist.forEach { track ->
            val trackView = layoutInflater.inflate(R.layout.item_track, tracksList, false)
            tracksList.addView(trackView)
            trackView.tvTitle.text = track.title
            trackView.setOnClickListener {
                track.play()
            }
        }
    }

    override fun updateTitle(title: String) =
        updateTitleView(title)

    override fun updateTimeline(progress: Float, currentTime: String) = Unit

    override fun updatePlaying(state: PlayingState) =
        updatePlayingButtons(state)

    private fun initIntroView() {
        btnPlay.setBackgroundResource(R.drawable.icon_play)
        btnPlay.setOnClickListener {
            mPlayerPresenter.onStop()
        }

        introClickableBackground.setOnClickListener {
            startActivity(Intent(this, PlayerActivity::class.java))
        }
    }

    private fun updateTitleView(title: String) {
        tvIntroTitle.text = title
    }

    private fun updatePlayingButtons(state: PlayingState) {
        when (state) {
            PlayingState.Disabled -> {
                btnPlay.disable()
                btnPlay.setBackgroundResource(R.drawable.icon_play)
            }
            PlayingState.Idle -> {
                btnPlay.enable()
                btnPlay.setBackgroundResource(R.drawable.icon_play)
            }
            PlayingState.Playing -> {
                btnPlay.enable()
                btnPlay.setBackgroundResource(R.drawable.icon_pause)
            }
            PlayingState.Paused -> {
                btnPlay.enable()
                btnPlay.setBackgroundResource(R.drawable.icon_play)
            }
        }
    }

}