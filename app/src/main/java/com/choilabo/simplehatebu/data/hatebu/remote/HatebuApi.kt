package com.choilabo.simplehatebu.data.hatebu.remote

import com.choilabo.simplehatebu.data.hatebu.dto.HatebuResponse
import retrofit2.Retrofit
import retrofit2.http.GET
import javax.inject.Inject

/**
 * Created by sato_shinichiro on 2020/09/29
 */
class HatebuApi private constructor(
    private val service: Service
) {

    @Inject
    constructor(retrofit: Retrofit) : this(retrofit.create(Service::class.java))

    suspend fun getHotentory(): HatebuResponse = service.getHotentry()

    private interface Service {

        @GET("hotentry.rss")
        suspend fun getHotentry(): HatebuResponse
    }
}