package com.ipsmobile.uinavigation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.next_screen.*

class NextScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.next_screen)

        val text = intent.extras?.getString(MainScreenActivity.EXTRA_ADDITIONAL_DATA)
        resultText?.text = text
    }

    fun onBack(view: View) {
        finish()
    }
}
