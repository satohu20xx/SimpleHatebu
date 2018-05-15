package com.choilabo.simplehatebu.data.bookmark

import com.choilabo.simplehatebu.data.bookmark.local.BookmarkDb
import com.choilabo.simplehatebu.data.bookmark.local.BookmarkDbClient
import com.choilabo.simplehatebu.data.bookmark.remote.BookmarkApi
import com.choilabo.simplehatebu.data.bookmark.remote.BookmarkApiClient
import dagger.Binds
import dagger.Module


/**
 * Created by sato_shinichiro on 2018/01/11.
 */
@Module
abstract class BookmarkModule {

    @Binds
    abstract fun provideBookmarkApi(bookmarkApiClient: BookmarkApiClient): BookmarkApi

    @Binds
    abstract fun provideBookmarkDb(bookmarkDbClient: BookmarkDbClient): BookmarkDb
}