package com.ipsmobile.uinavigation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class NextScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.next_screen)

        val viewText = findViewById<TextView>(R.id.resultText)
        val text = intent.extras?.getString(MainScreenActivity.EXTRA_ADDITIONAL_DATA)
        viewText?.text = text
    }

    fun onBack(view: View) {
        finish()
    }
}
