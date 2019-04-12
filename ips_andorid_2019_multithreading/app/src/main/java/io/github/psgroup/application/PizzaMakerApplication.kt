package io.github.psgroup.application

import android.app.Application
import io.github.psgroup.model.AuthorizationModel
import io.github.psgroup.model.CookModel

class PizzaMakerApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        authorizationModel = AuthorizationModel()
        cookModel = CookModel()
    }

    companion object {
        lateinit var authorizationModel: AuthorizationModel
            private set
        lateinit var cookModel: CookModel
            private set
    }

}