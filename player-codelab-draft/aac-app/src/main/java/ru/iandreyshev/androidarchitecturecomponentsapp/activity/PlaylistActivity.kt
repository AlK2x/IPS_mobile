package ru.iandreyshev.androidarchitecturecomponentsapp.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_playlist.*
import kotlinx.android.synthetic.main.item_track.view.*
import ru.iandreyshev.androidarchitecturecomponentsapp.R
import ru.iandreyshev.androidarchitecturecomponentsapp.viewModel.PlaylistViewModel
import ru.iandreyshev.model.player.PlayingState
import ru.iandreyshev.androidarchitecturecomponentsapp.application.MusicApplication
import ru.iandreyshev.androidarchitecturecomponentsapp.presenter.PlayerPresenter
import ru.iandreyshev.androidarchitecturecomponentsapp.presenter.PlaylistPresenter
import ru.iandreyshev.utils.disable
import ru.iandreyshev.utils.enable

class PlaylistActivity : AppCompatActivity() {

    private lateinit var mPlaylistPresenter: PlaylistPresenter
    private lateinit var mPlayerPresenter: PlayerPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playlist)

        val mViewModel = ViewModelProviders.of(this).get(PlaylistViewModel::class.java)
        mPlaylistPresenter = MusicApplication.getPlaylistPresenter(mViewModel)
        mPlayerPresenter = MusicApplication.getPlayerPresenter(mViewModel)
        mViewModel.trackTitle.observe(this, Observer { newTitle ->
            updateTitleView(newTitle.orEmpty())
        })
        mViewModel.trackPlayingState.observe(this, Observer { state ->
            if (state != null) {
                updatePlayingButtons(state)
            }
        })
        mViewModel.playlist.observe(this, Observer { playlist ->
            tracksList.removeAllViews()

            playlist?.forEach { track ->
                val trackView = layoutInflater.inflate(R.layout.item_track, tracksList, false)
                tracksList.addView(trackView)
                trackView.tvTitle.text = track.title
                trackView.setOnClickListener {
                    track.play()
                }
            }
        })

        initIntroView()
    }

    private fun initIntroView() {
        btnPlay.setBackgroundResource(R.drawable.icon_play)
        btnPlay.setOnClickListener {
            mPlayerPresenter.onPlay()
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
