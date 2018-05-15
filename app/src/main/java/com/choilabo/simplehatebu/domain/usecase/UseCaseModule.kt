package com.choilabo.simplehatebu.domain.usecase

import com.choilabo.simplehatebu.domain.usecase.bookmark.*
import dagger.Binds
import dagger.Module


/**
 * Created by sato_shinichiro on 2018/01/12.
 */
@Module
abstract class UseCaseModule {

    @Binds
    abstract fun provideFetchBookmarks(fetchBookmarksImpl: FetchBookmarksImpl): FetchBookmarks

    @Binds
    abstract fun provideObserveUnreadBookmark(observeUnreadBookmarkImpl: ObserveUnreadBookmarkImpl): ObserveUnreadBookmark

    @Binds
    abstract fun provideSetBookmarkShownByLinks(setBookmarkShownByLinksImpl: SetBookmarkShownByLinksImpl): SetBookmarkShownByLinks
}