package com.choilabo.simplehatebu.data.bookmark.remote

import io.reactivex.Single


/**
 * Created by sato_shinichiro on 2018/01/11.
 */
interface BookmarkApi {

    fun getHotentry(): Single<BookmarkResponse>

    fun getHotentryByCategory(category: String, threshold: Int): Single<BookmarkResponse>
}