package com.choilabo.simplehatebu

import com.choilabo.simplehatebu.data.realm.RealmProvider
import com.choilabo.simplehatebu.di.component.DaggerAppComponent
import com.choilabo.simplehatebu.service.GetHotentoryJobService
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber
import javax.inject.Inject


/**
 * Created by sato_shinichiro on 2018/01/11.
 */
class App : DaggerApplication() {

    @Inject
    lateinit var realmProvider: RealmProvider

    override fun onCreate() {
        super.onCreate()

        realmProvider.reserve()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        GetHotentoryJobService.start(this)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder()
                .create(this)
    }
}