package com.choilabo.simplehatebu.domain.usecase.bookmark

import com.choilabo.simplehatebu.data.bookmark.constant.BookmarkCategory
import com.choilabo.simplehatebu.domain.repository.bookmark.BookmarkRepository
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by sato_shinichiro on 2018/01/11.
 */
interface FetchBookmarks {

    operator fun invoke(): Completable
}

@Singleton
class FetchBookmarksImpl @Inject constructor(
        private val bookmarkRepository: BookmarkRepository
) : FetchBookmarks {

    override fun invoke(): Completable {
        return Completable.mergeArray(
                bookmarkRepository.fetchHotentory(),
                Observable.fromIterable(BookmarkCategory.values().asIterable())
                        .filter { it != BookmarkCategory.Hotentry }
                        .flatMapCompletable {
                            bookmarkRepository.fetchHotentoryByCategory(it, 30)
                        }
        )
    }
}