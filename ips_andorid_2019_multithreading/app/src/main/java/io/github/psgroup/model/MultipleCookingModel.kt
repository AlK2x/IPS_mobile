package io.github.psgroup.model

import android.os.AsyncTask
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class MultipleCookingModel {
    private var mExecutor: Executor = Executors.newFixedThreadPool(3)

    private var mPresenter: MultipleCookingModel.IPresenter? = null

    private var mTaskMap: MutableMap<Int, AsyncTask<*, *, *>> = mutableMapOf()

    interface IPresenter {
        fun update(cookingState: CookingState, taskId: Int)
    }

    fun start(taskId: Int) {
        if (mTaskMap[taskId] != null && mTaskMap[taskId]?.status == AsyncTask.Status.RUNNING) {
            return
        }
        mTaskMap[taskId] = SingleTask(taskId)
        mTaskMap[taskId]?.executeOnExecutor(mExecutor)
    }

    fun subscribe(presenter: MultipleCookingModel.IPresenter) {
        mPresenter = presenter
    }

    fun unsubscribe() {
        mPresenter = null
    }

    inner class SingleTask(taskId: Int): AsyncTask<Void, Int, CookingState>() {

        private val mTaskId = taskId

        override fun onPreExecute() {
            super.onPreExecute()
            val state = CookingState.InProgress(CookModel.MAX_PROGRESS, 0)
            mPresenter?.update(state, mTaskId)
        }

        override fun doInBackground(vararg params: Void?): CookingState {
            var progress = CookModel.MIN_PROGRESS
            Thread.sleep(100)

            while (progress < CookModel.MAX_PROGRESS) {
                Thread.sleep(100)
                progress += CookModel.PROGRESS_STEP

                publishProgress(progress)
            }

            return CookingState.Completed
        }

        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)
            val progress = values.getOrNull(0) ?: 0
            val state = CookingState.InProgress(CookModel.MAX_PROGRESS, progress)
            mPresenter?.update(state, mTaskId)
        }

        override fun onPostExecute(result: CookingState?) {
            super.onPostExecute(result)
            val state = result ?: return
            mPresenter?.update(state, mTaskId)
        }
    }
}