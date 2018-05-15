package com.choilabo.simplehatebu.service

import android.content.Context
import com.choilabo.simplehatebu.domain.usecase.bookmark.FetchBookmarks
import com.firebase.jobdispatcher.*
import dagger.android.AndroidInjection
import dagger.android.ContributesAndroidInjector
import timber.log.Timber
import javax.inject.Inject


/**
 * Created by sato_shinichiro on 2018/01/11.
 */

class GetHotentoryJobService : SimpleJobService() {

    @Inject
    lateinit var fetchBookmarks: FetchBookmarks

    companion object {
        fun start(context: Context) {
            val dispatcher = FirebaseJobDispatcher(GooglePlayDriver(context))
            dispatcher.newJobBuilder()
                    .setService(GetHotentoryJobService::class.java)
                    .setTag(GetHotentoryJobService::class.java.simpleName)
                    .setReplaceCurrent(false)
                    .setConstraints(Constraint.ON_ANY_NETWORK)
                    .setTrigger(Trigger.executionWindow(3600, 4000))
                    .setRecurring(true)
                    .build()
                    .run { dispatcher.mustSchedule(this) }
        }
    }

    override fun onCreate() {
        AndroidInjection.inject(this)
        super.onCreate()
    }

    override fun onRunJob(job: com.firebase.jobdispatcher.JobParameters?): Int {
        fetchBookmarks()
                .doOnError { Timber.e(it) }
                .onErrorComplete()
                .blockingAwait()
        return 0
    }


    @dagger.Module
    abstract class Module {

        @ContributesAndroidInjector
        abstract fun contributesAndroidInjector(): GetHotentoryJobService
    }
}