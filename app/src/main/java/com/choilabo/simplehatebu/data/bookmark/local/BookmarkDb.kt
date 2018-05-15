package com.choilabo.simplehatebu.data.bookmark.local

import com.choilabo.simplehatebu.data.bookmark.entity.Bookmark
import com.choilabo.simplehatebu.data.bookmark.remote.BookmarkResponse
import io.reactivex.Flowable


/**
 * Created by sato_shinichiro on 2018/01/11.
 */
interface BookmarkDb {

    fun observeByUnread(): Flowable<List<Bookmark>>

    fun setShownByLinks(links: List<String>)

    fun save(filters: List<String>, response: BookmarkResponse)
}