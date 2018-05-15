package com.choilabo.simplehatebu.di.module

import android.content.Context
import com.choilabo.simplehatebu.App
import com.choilabo.simplehatebu.data.DataModule
import com.choilabo.simplehatebu.domain.repository.RepositoryModule
import com.choilabo.simplehatebu.domain.usecase.UseCaseModule
import dagger.Module
import dagger.Provides
import dagger.android.support.AndroidSupportInjectionModule


/**
 * Created by sato_shinichiro on 2018/01/11.
 */
@Module(includes = arrayOf(
        AndroidSupportInjectionModule::class,
        ActivityModule::class,
        ServiceModule::class,
        DataModule::class,
        RepositoryModule::class,
        UseCaseModule::class
))
class AppModule {

    @Provides
    fun provideContext(app: App): Context = app

}