package com.choilabo.simplehatebu.domain.repository.bookmark

import com.choilabo.simplehatebu.data.bookmark.constant.BookmarkCategory
import com.choilabo.simplehatebu.data.bookmark.entity.Bookmark
import io.reactivex.Completable
import io.reactivex.Flowable


/**
 * Created by sato_shinichiro on 2018/01/11.
 */
interface BookmarkRepository {

    fun observeByUnread(): Flowable<List<Bookmark>>

    fun setShownByLinks(links: List<String>): Completable

    fun fetchHotentory(): Completable

    fun fetchHotentoryByCategory(category: BookmarkCategory, threshold: Int): Completable
}