package com.ipsmobile.uinavigation

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import java.util.*
import kotlin.concurrent.schedule

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        Timer("Loading...", false).schedule(3000) {
            startActivity(Intent(this@SplashScreenActivity, MainScreenActivity::class.java))
        }
    }
}
