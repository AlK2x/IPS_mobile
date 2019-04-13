package io.github.psgroup.application

import android.app.Application
import io.github.psgroup.model.AuthorizationModel
import io.github.psgroup.model.CookModel
import io.github.psgroup.model.MultipleCookingModel

class PizzaMakerApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        authorizationModel = AuthorizationModel()
        cookModel = CookModel()
        multipleCookingModel = MultipleCookingModel()
    }

    companion object {
        lateinit var authorizationModel: AuthorizationModel
            private set
        lateinit var cookModel: CookModel
            private set
        lateinit var multipleCookingModel: MultipleCookingModel
            private set
    }

}