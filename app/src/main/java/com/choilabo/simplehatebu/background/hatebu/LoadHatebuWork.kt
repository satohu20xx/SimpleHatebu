package com.choilabo.simplehatebu.background.hatebu

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.choilabo.simplehatebu.data.hatebu.HatebuDataModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

/**
 * Created by sato_shinichiro on 2020/09/30
 */
@HiltWorker
class LoadHatebuWork @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val hatebuDataModel: HatebuDataModel
) : CoroutineWorker(context, params) {

    companion object {
        const val UNIQUE_NAME = "load_hatebu_worker"
    }

    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO) {
            try {
                hatebuDataModel.load()
                Result.success()
            } catch (e: Throwable) {
                Timber.e(e)
                Result.failure()
            }
        }
    }
}