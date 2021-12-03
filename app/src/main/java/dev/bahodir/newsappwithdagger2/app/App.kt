package dev.bahodir.newsappwithdagger2.app

import android.app.Application
import com.yariksoffice.lingver.Lingver
import dev.bahodir.newsappwithdagger2.di.component.AppComponent
import dev.bahodir.newsappwithdagger2.di.component.DaggerAppComponent
import dev.bahodir.newsappwithdagger2.di.module.DatabaseModule

class App : Application() {
    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        Lingver.init(this, "en")
        appComponent =
            DaggerAppComponent.builder().databaseModule(DatabaseModule(applicationContext)).build()
    }

}