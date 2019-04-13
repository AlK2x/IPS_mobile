package io.github.psgroup.presentation

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ProgressBar
import io.github.psgroup.R
import io.github.psgroup.application.PizzaMakerApplication
import io.github.psgroup.model.CookModel
import io.github.psgroup.model.CookingState
import io.github.psgroup.model.MultipleCookingModel
import kotlinx.android.synthetic.main.activity_cook.*
import kotlinx.android.synthetic.main.activity_multiple_cooking.*

class MultipleCookingActivity : AppCompatActivity(), MultipleCookingModel.IPresenter {

    private val mModel by lazy { PizzaMakerApplication.multipleCookingModel }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multiple_cooking)

        runOneBtn.setOnClickListener {
            mModel.start(1)
        }
        runTwoBtn.setOnClickListener {
            mModel.start(2)
        }
        runThreeBtn.setOnClickListener {
            mModel.start(3)
        }
    }

    override fun onResume() {
        super.onResume()
        mModel.subscribe(this)
    }

    override fun onPause() {
        super.onPause()
        mModel.unsubscribe()
    }

    override fun update(cookingState: CookingState, taskId: Int) {
        when (taskId) {
            1 -> updateProgress(progressOne, cookingState)
            2 -> updateProgress(progressTwo, cookingState)
            3 -> updateProgress(progressThree, cookingState)
        }
    }

    private fun updateProgress(progress: ProgressBar, cookingState: CookingState) {
        when (cookingState) {
            CookingState.NotStarted -> {
                progress.max = 0
                progress.progress = 0
            }
            is CookingState.InProgress -> {
                progress.max = cookingState.max
                progress.progress = cookingState.current
            }
            is CookingState.Completed -> {
                progress.max = CookModel.MAX_PROGRESS
                progress.progress = CookModel.MAX_PROGRESS
            }
            is CookingState.Error -> {
                progress.max = 0
                progress.progress = 0
            }
        }
    }
}