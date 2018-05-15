package com.choilabo.simplehatebu.domain.repository.bookmark

import com.choilabo.simplehatebu.data.bookmark.constant.BookmarkCategory
import com.choilabo.simplehatebu.data.bookmark.entity.Bookmark
import com.choilabo.simplehatebu.data.bookmark.local.BookmarkDb
import com.choilabo.simplehatebu.data.bookmark.remote.BookmarkApi
import com.choilabo.simplehatebu.data.linkfilter.local.LinkFilterDb
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by sato_shinichiro on 2018/01/11.
 */
@Singleton
class BookmarkRepositoryClient @Inject constructor(
        private val bookmarkApi: BookmarkApi,
        private val bookmarkDb: BookmarkDb,
        private val linkFilterDb: LinkFilterDb
) : BookmarkRepository {

    override fun observeByUnread(): Flowable<List<Bookmark>> {
        return bookmarkDb.observeByUnread()
    }

    override fun setShownByLinks(links: List<String>): Completable {
        return Completable.fromAction {
            bookmarkDb.setShownByLinks(links)
        }
    }

    override fun fetchHotentory(): Completable {
        return bookmarkApi.getHotentry()
                .subscribeOn(Schedulers.io())
                .flatMapCompletable {
                    Completable.fromAction {
                        bookmarkDb.save(
                                linkFilterDb.getAll(),
                                it
                        )
                    }
                }
    }

    override fun fetchHotentoryByCategory(category: BookmarkCategory, threshold: Int): Completable {
        return bookmarkApi.getHotentryByCategory(category.getKey(), threshold)
                .subscribeOn(Schedulers.io())
                .flatMapCompletable {
                    Completable.fromAction {
                        bookmarkDb.save(
                                linkFilterDb.getAll(),
                                it
                        )
                    }
                }
    }
}