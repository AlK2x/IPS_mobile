package ips.mobile.gitrockstars.di

import dagger.Component
import ips.mobile.gitrockstars.ui.search.SearchFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(fragment: SearchFragment)

}