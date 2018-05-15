package com.choilabo.simplehatebu.data.linkfilter.local

import io.reactivex.Completable


/**
 * Created by sato_shinichiro on 2018/01/11.
 */
interface LinkFilterDb {

    fun getAll(): List<String>

    fun save(startWith: String): Completable
}