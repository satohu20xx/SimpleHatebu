package com.choilabo.simplehatebu.background.hatebu

import android.content.Context
import androidx.work.*
import dagger.Reusable
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by sato_shinichiro on 2020/09/30
 */
@Reusable
class HatebuWorkerController @Inject constructor(
    @ApplicationContext private val context: Context
) {

    fun startLoadWorker() {
        PeriodicWorkRequestBuilder<LoadHatebuWork>(1, TimeUnit.HOURS)
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()
            )
            .build()
            .also {
                WorkManager.getInstance(context)
                    .enqueueUniquePeriodicWork(
                        LoadHatebuWork.UNIQUE_NAME,
                        ExistingPeriodicWorkPolicy.REPLACE,
                        it
                    )
            }
    }
}