package com.choilabo.simplehatebu.di.component

import com.choilabo.simplehatebu.App
import com.choilabo.simplehatebu.di.module.AppModule
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton


/**
 * Created by sato_shinichiro on 2018/01/11.
 */
@Singleton
@Component(modules = arrayOf(
        AppModule::class
))
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()
}