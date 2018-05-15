package com.choilabo.simplehatebu.data.realm

import dagger.Binds
import dagger.Module


/**
 * Created by sato_shinichiro on 2018/01/11.
 */
@Module
abstract class RealmModule {

    @Binds
    abstract fun provideRealmProvider(realmProviderClient: RealmProviderClient): RealmProvider
}