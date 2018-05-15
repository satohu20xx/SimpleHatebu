package com.choilabo.simplehatebu.domain.repository

import com.choilabo.simplehatebu.domain.repository.bookmark.BookmarkRepository
import com.choilabo.simplehatebu.domain.repository.bookmark.BookmarkRepositoryClient
import dagger.Binds
import dagger.Module


/**
 * Created by sato_shinichiro on 2018/01/12.
 */
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideBookmarkRepository(bookmarkRepositoryClient: BookmarkRepositoryClient): BookmarkRepository
}