package com.choilabo.simplehatebu.data.realm

import io.realm.Realm


/**
 * Created by sato_shinichiro on 2018/01/11.
 */
interface RealmProvider {

    fun reserve()

    fun release()

    fun get(): Realm

    fun executeTransaction(transaction: Realm.Transaction)
}