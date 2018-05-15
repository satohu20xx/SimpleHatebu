package com.choilabo.simplehatebu.data.realm

import android.content.Context
import io.realm.Realm
import io.realm.RealmConfiguration
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by sato_shinichiro on 2018/01/11.
 */
@Singleton
class RealmProviderClient @Inject constructor(
        context: Context
) : RealmProvider {

    private val realmMap = hashMapOf<Long, MutableList<Realm>>()

    init {
        Realm.init(context)
        RealmConfiguration.Builder()
                .build()
                .run {
                    Realm.compactRealm(this)
                    Realm.setDefaultConfiguration(this)
                }
    }

    @Synchronized
    override fun reserve() {
        val threadId = Thread.currentThread().id
        if (realmMap.get(threadId) == null) {
            realmMap.set(threadId, mutableListOf())
        }
        realmMap.get(threadId)?.add(Realm.getDefaultInstance())
    }

    @Synchronized
    override fun release() {
        val threadId = Thread.currentThread().id
        realmMap.get(threadId)?.takeUnless { it.isEmpty() }?.run {
            last().close()
            removeAt(lastIndex)
        } ?: Timber.w("This thread's realm is already released")
    }

    override fun get(): Realm {
        val threadId = Thread.currentThread().id
        return realmMap.get(threadId)?.first() ?: throw Exception("This thread realm is not reserved. call reserve()")
    }

    override fun executeTransaction(transaction: Realm.Transaction) {
        Realm.getDefaultInstance().run {
            executeTransaction(transaction)
            close()
        }
    }
}