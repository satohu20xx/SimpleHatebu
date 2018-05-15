package com.choilabo.simplehatebu.data.linkfilter.local

import com.choilabo.simplehatebu.data.linkfilter.entity.LinkFilter
import com.choilabo.simplehatebu.data.realm.RealmProvider
import io.reactivex.Completable
import io.realm.Realm
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by sato_shinichiro on 2018/01/11.
 */
@Singleton
class LinkFilterDbClient @Inject constructor(
        private val realmProvider: RealmProvider
) : LinkFilterDb {

    override fun getAll(): List<String> {
        realmProvider.reserve()
        val filters = realmProvider.get().where(LinkFilter::class.java).findAll().map { it.startWith }
        realmProvider.release()
        return filters
    }

    override fun save(startWith: String): Completable {
        return Completable.fromAction {
            realmProvider.executeTransaction(Realm.Transaction { realm ->
                val results = realm.where(LinkFilter::class.java)
                        .equalTo("startWith", startWith)
                        .findAll()

                if (results.isEmpty()) {
                    realm.createObject(LinkFilter::class.java, startWith)
                }
            })
        }
    }
}