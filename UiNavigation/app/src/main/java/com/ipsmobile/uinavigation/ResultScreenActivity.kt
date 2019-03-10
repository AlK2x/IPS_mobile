package com.ipsmobile.uinavigation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class ResultScreenActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ADDITIONAL_DATA = "extra_additional_data"

        fun createIntent(context: Context, additionalData: String): Intent {
            val intent = Intent(context, MainScreenActivity::class.java)
            intent.putExtra(EXTRA_ADDITIONAL_DATA, additionalData)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.result_screen)
    }

    fun onReturnResultToMain(view: View) {
        val textField = findViewById<EditText>(R.id.resultInput)
        val result = textField?.text.toString()
        setResult(Activity.RESULT_OK, createIntent(this, result))
        this.finish()
    }
}
