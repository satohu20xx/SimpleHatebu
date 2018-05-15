package com.choilabo.simplehatebu.data.bookmark.remote

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by sato_shinichiro on 2018/01/11.
 */
@Singleton
class BookmarkApiClient @Inject constructor(
        retrofit: Retrofit
) : BookmarkApi {

    private val service: Service = retrofit.create(Service::class.java)

    override fun getHotentry(): Single<BookmarkResponse> {
        return service.getHotentry()
    }

    override fun getHotentryByCategory(category: String, threshold: Int): Single<BookmarkResponse> {
        return service.getHotentry(category, threshold)
    }

    interface Service {

        @GET("/hotentry.rss")
        fun getHotentry(): Single<BookmarkResponse>

        @GET("/entrylist/{category}.rss")
        fun getHotentry(
                @Path("category") category: String,
                @Query("threshold") threshold: Int): Single<BookmarkResponse>
    }
}