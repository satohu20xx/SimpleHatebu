package com.choilabo.simplehatebu.data.linkfilter

import com.choilabo.simplehatebu.data.linkfilter.local.LinkFilterDb
import com.choilabo.simplehatebu.data.linkfilter.local.LinkFilterDbClient
import dagger.Binds
import dagger.Module


/**
 * Created by sato_shinichiro on 2018/03/27.
 */
@Module
abstract class LinkFilterModule {

    @Binds
    abstract fun provideLinkFilterDb(linkFilterDbClient: LinkFilterDbClient): LinkFilterDb
}