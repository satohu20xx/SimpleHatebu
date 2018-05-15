package com.choilabo.simplehatebu.ui.main

import android.arch.lifecycle.ViewModel
import com.choilabo.simplehatebu.data.bookmark.entity.Bookmark
import com.choilabo.simplehatebu.domain.usecase.bookmark.FetchBookmarks
import com.choilabo.simplehatebu.domain.usecase.bookmark.ObserveUnreadBookmark
import com.choilabo.simplehatebu.domain.usecase.bookmark.SetBookmarkShownByLinks
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject


/**
 * Created by sato_shinichiro on 2018/01/12.
 */
class MainViewModel @Inject constructor(
        private val fetchBookmarks: FetchBookmarks,
        private val observeUnreadBookmark: ObserveUnreadBookmark,
        private val setBookmarkShownByLinks: SetBookmarkShownByLinks
) : ViewModel() {

    fun setShownBookmarkByIds(bookmarks: List<Bookmark>): Completable {
        return setBookmarkShownByLinks.invoke(bookmarks.map { it.link })
    }

    fun fetchBookmark(): Completable {
        return fetchBookmarks.invoke()
    }

    fun observeUnreadBookmark(): Flowable<List<Bookmark>> {
        return observeUnreadBookmark.invoke()
    }
}