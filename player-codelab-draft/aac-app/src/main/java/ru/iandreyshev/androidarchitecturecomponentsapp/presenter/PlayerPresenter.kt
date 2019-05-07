package ru.iandreyshev.androidarchitecturecomponentsapp.presenter

import ru.iandreyshev.model.player.Player

class PlayerPresenter(private val player: Player) {

    fun onPlay() = player.onPlay()

    fun onStop() = player.onStop()

    fun onRestart() = player.onRestart()

    fun onChangeTimelinePosition(timePercent: Float) = player.onChangeTimelinePosition(timePercent)
}