package com.choilabo.simplehatebu.data

import com.choilabo.simplehatebu.data.bookmark.BookmarkModule
import com.choilabo.simplehatebu.data.linkfilter.LinkFilterModule
import com.choilabo.simplehatebu.data.realm.RealmModule
import dagger.Module


/**
 * Created by sato_shinichiro on 2018/01/12.
 */
@Module(includes = arrayOf(
        ApiModule::class,
        BookmarkModule::class,
        LinkFilterModule::class,
        RealmModule::class
))
abstract class DataModule