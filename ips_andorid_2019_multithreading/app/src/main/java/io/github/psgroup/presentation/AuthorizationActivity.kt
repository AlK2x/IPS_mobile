package io.github.psgroup.presentation

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.github.psgroup.R
import io.github.psgroup.application.PizzaMakerApplication
import io.github.psgroup.model.AuthorizationModel
import kotlinx.android.synthetic.main.activity_authorization.*

class AuthorizationActivity : AppCompatActivity(), AuthorizationModel.IPresenter {

    private val mModel by lazy { PizzaMakerApplication.authorizationModel }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorization)

        button.setOnClickListener {
            val login = loginInput?.text.toString()
            val password = passwordInput?.text.toString()
            mModel.signIn(login, password)
        }
    }

    override fun waitSignIn(isWait: Boolean) {
        if (isWait) {
            button.isEnabled = false
        }
    }

    override fun signInCompleted() {
        val intent = Intent(this, CookActivity::class.java)
        this.startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        mModel.subscribe(this)
    }

    override fun onPause() {
        super.onPause()
        mModel.unsubscribe()
    }

}
