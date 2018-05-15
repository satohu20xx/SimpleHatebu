package com.choilabo.simplehatebu.domain.usecase.bookmark

import com.choilabo.simplehatebu.domain.repository.bookmark.BookmarkRepository
import io.reactivex.Completable
import javax.inject.Inject


/**
 * Created by sato_shinichiro on 2018/03/29.
 */
interface SetBookmarkShownByLinks {

    operator fun invoke(links: List<String>): Completable
}

class SetBookmarkShownByLinksImpl @Inject constructor(
        private val bookmarkRepository: BookmarkRepository
) : SetBookmarkShownByLinks {

    override fun invoke(links: List<String>): Completable {
        return bookmarkRepository.setShownByLinks(links)
    }
}