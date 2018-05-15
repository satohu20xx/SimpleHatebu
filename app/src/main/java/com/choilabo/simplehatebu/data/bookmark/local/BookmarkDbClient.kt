package com.choilabo.simplehatebu.data.bookmark.local

import com.choilabo.simplehatebu.data.bookmark.entity.Bookmark
import com.choilabo.simplehatebu.data.bookmark.remote.BookmarkResponse
import com.choilabo.simplehatebu.data.realm.RealmProvider
import io.reactivex.Flowable
import io.realm.Realm
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by sato_shinichiro on 2018/01/11.
 */
@Singleton
class BookmarkDbClient @Inject constructor(
        private val realmProvider: RealmProvider
) : BookmarkDb {

    override fun observeByUnread(): Flowable<List<Bookmark>> {
        return realmProvider.get()
                .where(Bookmark::class.java)
                .equalTo("shown", false)
                .equalTo("deleted", false)
                .findAll()
                .asFlowable()
                .map { it.toList() }
    }

    override fun setShownByLinks(links: List<String>) {
        realmProvider.executeTransaction(Realm.Transaction { realm ->
            val bookmarks = realm.where(Bookmark::class.java)
                    .`in`("link", links.toTypedArray())
                    .findAll()
            bookmarks.forEach { it.shown = true }
            realm.insertOrUpdate(bookmarks)
        })
    }

    override fun save(filters: List<String>, response: BookmarkResponse) {
        realmProvider.executeTransaction(Realm.Transaction { realm ->
            val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.JAPAN)
            response.item
                    .filter { item -> filters.none { item.link.startsWith(it) } }
                    .filter { item -> realm.where(Bookmark::class.java).equalTo("link", item.link).findAll().isEmpty() }
                    .forEach { item ->
                        realm.createObject(Bookmark::class.java, item.link)
                                .run {
                                    title = item.title ?: ""
                                    contentSnippet = item.description ?: ""
                                    category = item.subject ?: ""
                                    publishedData = format.parse(item.date).time
                                    faviconUrl = regExp(item.encoded, "<cite><img src=\"([^\" ]+)\" alt=")
                                    thumbnailUrl = regExp(item.encoded, "<p><a href=\"${item.link}\"><img src=\"([^\" ]+)\" alt=")
                                    shown = false
                                    deleted = false
                                    createdAt = System.currentTimeMillis()
                                    realm.insertOrUpdate(this)
                                }
                    }
        })
    }

    private fun regExp(content: String, filter: String): String {
        return Pattern.compile(filter).matcher(content)
                .let { if (it.find()) it.group(1) else "" }
    }
}