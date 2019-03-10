package com.ipsmobile.lifecycle

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var mResult: Int = 0

    public override fun onSaveInstanceState(savedInstanceState: Bundle) {
        savedInstanceState.putInt("STATE", mResult)
        super.onSaveInstanceState(savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mResult = savedInstanceState?.getInt("STATE")?: 0
        ++mResult
        printToast()
    }

    override fun onStart() {
        super.onStart()
        ++mResult
        printToast()
    }
    override fun onResume() {
        super.onResume()
        ++mResult
        printToast()
    }
    override fun onPause() {
        super.onPause()
        --mResult
        printToast()
    }
    override fun onStop() {
        super.onStop()
        --mResult
        printToast()
    }
    override fun onDestroy() {
        super.onDestroy()
        --mResult
        printToast()
    }
    override fun onRestart() {
        super.onRestart()
        mResult += 2
        printToast()
    }

    fun printToast() {
        val toast: Toast = Toast.makeText(this, mResult.toString(), Toast.LENGTH_SHORT)
        toast.show()
    }
}
