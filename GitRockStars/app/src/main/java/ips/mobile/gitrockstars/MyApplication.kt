package ips.mobile.gitrockstars

import android.app.Application
import ips.mobile.gitrockstars.di.AppComponent
import ips.mobile.gitrockstars.di.AppModule
import ips.mobile.gitrockstars.di.DaggerAppComponent

class MyApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        initComponents()
    }

    private fun initComponents() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

}