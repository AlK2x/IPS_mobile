package ru.iandreyshev.mymusicapplication.presenter

import ru.iandreyshev.model.player.IPlayerPresenter
import ru.iandreyshev.model.player.PlayingState
import ru.iandreyshev.model.player.Timeline

interface IView {
    fun updateTitle(title: String)
    fun updateTimeline(progress: Float, currentTime: String)
    fun updatePlaying(state: PlayingState)
}

class PlayerPresenter(
    private val resources: Resources
) : IPlayerPresenter {

    private var mTitle: String = ""
    private var mTimeline: Timeline = Timeline(0, 0f)
    private var mPlayingState: PlayingState = PlayingState.Disabled
    private val mViewMap = mutableMapOf<IView, Boolean>()

    fun onPlay() = player.onPlay()
    fun onStop() = player.onStop()
    fun onRestart() = player.onRestart()
    fun onChangeTimePosition(timePercent: Float) = player.onChangeTimelinePosition(timePercent)

    fun onAttach(view: IView) {
        mViewMap[view] = true

        view.updateTitle(mTitle)
        val time = mTimeline.timeInMillis.toString()
        val progress = mTimeline.percent
        view.updateTimeline(progress, time)
        view.updatePlaying(mPlayingState)
    }

    fun onDetach(view: IView) {
        if (mViewMap.contains(view)) {
            mViewMap[view] = false
        }
    }

    fun onFinish(view: IView) {
        mViewMap.remove(view)
    }

    override fun updateTitle(title: String?) {
        mTitle = title ?: resources.getString(R.string.player_song_not_selected)

        updateView { view ->
            view.updateTitle(mTitle)
        }
    }

    override fun updateTimeline(timeline: Timeline) {
        mTimeline = timeline

        updateView { view ->
            val time = mTimeline.timeInMillis.toString()
            val progress = mTimeline.percent
            view.updateTimeline(progress, time)
        }
    }

    override fun updatePlaying(state: PlayingState) {
        mPlayingState = state

        updateView { view ->
            view.updatePlaying(mPlayingState)
        }
    }

    private fun updateView(updateCallback: (IView) -> Unit) {
        mViewMap.entries.forEach {
            if (it.value) {
                updateCallback(it.key)
            }
        }
    }
}
