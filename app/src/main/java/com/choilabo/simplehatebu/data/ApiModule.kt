package com.choilabo.simplehatebu.data

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory


/**
 * Created by sato_shinichiro on 2018/01/11.
 */
@Module
class ApiModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://b.hatena.ne.jp")
                .client(
                        OkHttpClient.Builder()
                                .addInterceptor(HttpLoggingInterceptor()
                                        .setLevel(HttpLoggingInterceptor.Level.BASIC)
                                )
                                .build()
                )
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build()
    }
}