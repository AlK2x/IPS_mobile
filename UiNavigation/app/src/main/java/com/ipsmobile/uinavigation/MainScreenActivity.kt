package com.ipsmobile.uinavigation

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.main_screen.*

class MainScreenActivity : AppCompatActivity() {

    companion object {
        val EXTRA_ADDITIONAL_DATA = "main_activity_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_screen)
    }

    fun onTakeResultClick(view: View) {
        val intent = Intent(this@MainScreenActivity, ResultScreenActivity::class.java)
        startActivityForResult(intent, 101)
    }

    fun onNextScreenClick(view: View) {
        val intent = Intent(this@MainScreenActivity, NextScreenActivity::class.java)
        intent.putExtra(MainScreenActivity.EXTRA_ADDITIONAL_DATA, nextInput?.text.toString())
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            val result = data?.getStringExtra(ResultScreenActivity.EXTRA_ADDITIONAL_DATA)
            resultText?.text = result
        }
    }
}
