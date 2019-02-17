package com.ipsmobile.equationsolver

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.lang.StringBuilder
import java.util.*
import kotlin.math.pow
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {

    private var aParameterText: EditText? = null
    private var bParameterText: EditText? = null
    private var cParameterText: EditText? = null

    private var discriminantField: TextView? = null
    private var x1Field: TextView? = null
    private var x2Field: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val locale = Locale("ru")
        val config = baseContext.resources.configuration
        config.locale = locale
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)

        setContentView(R.layout.activity_main)

        this.discriminantField = findViewById(R.id.discriminant_text)
        this.x1Field = findViewById(R.id.x1_text)
        this.x2Field = findViewById(R.id.x2_text)

        this.aParameterText = findViewById(R.id.a_parameter)
        this.bParameterText = findViewById(R.id.b_paremater)
        this.cParameterText = findViewById(R.id.c_parameter)
    }

    fun onSolve(view: View) {
        if (!this.checkParamsAreCorrect()) {
            return
        }
        val a = this.aParameterText?.text.toString().toDouble()
        val b = this.bParameterText?.text.toString().toDouble()
        val c = this.cParameterText?.text.toString().toDouble()

        val d = getDiscriminant(a, b, c)
        val (x1, x2) = getSolutions(a, b, d)

        this.discriminantField?.setText(d.toString())
        this.x1Field?.setText(x1.toString())
        this.x2Field?.setText(x2.toString())
    }

    private fun checkParamsAreCorrect(): Boolean {
        val stringBuilder = StringBuilder()
        if (this.aParameterText?.text.toString().isEmpty()) {
            stringBuilder.append(getString(R.string.missed_parameter_error) + " A")
        }
        else {
            if (this.aParameterText?.text.toString().toDouble().compareTo(0.0) == 0) {
                stringBuilder.append(getString(R.string.wrong_first_parameter))
            }
        }

        if (this.bParameterText?.text.toString().isEmpty()) {
            stringBuilder.append(getString(R.string.missed_parameter_error) + " B")
        }
        if (this.cParameterText?.text.toString().isEmpty()) {
            stringBuilder.append(getString(R.string.missed_parameter_error) + " C")
        }

        if (stringBuilder.isNotEmpty()) {
            printToast(stringBuilder.toString())
        }

        return stringBuilder.isEmpty()
    }

    private fun getDiscriminant(a: Double, b: Double, c: Double): Double {
        return sqrt(b.pow(2) - 4 * a * c)
    }

    private fun getSolutions(a: Double, b: Double, d: Double): Pair<Double, Double> {
        return Pair((-1 * b + d) / (2 * a), (-1 * b - d) / 2 * a)
    }

    private fun printToast(msg: String) {
        val toast: Toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT)
        toast.show()
    }
}
