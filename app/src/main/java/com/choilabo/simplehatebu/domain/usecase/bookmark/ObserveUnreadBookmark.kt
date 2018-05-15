package com.choilabo.simplehatebu.domain.usecase.bookmark

import com.choilabo.simplehatebu.data.bookmark.entity.Bookmark
import com.choilabo.simplehatebu.domain.repository.bookmark.BookmarkRepository
import io.reactivex.Flowable
import javax.inject.Inject


/**
 * Created by sato_shinichiro on 2018/03/27.
 */
interface ObserveUnreadBookmark {

    operator fun invoke(): Flowable<List<Bookmark>>
}

class ObserveUnreadBookmarkImpl @Inject constructor(
        private val bookmarkRepository: BookmarkRepository
) : ObserveUnreadBookmark {

    override fun invoke(): Flowable<List<Bookmark>> {
        return bookmarkRepository.observeByUnread()
    }
}