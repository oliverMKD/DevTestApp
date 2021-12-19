package com.oli.testapp.core.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.oli.testapp.feature.presentation.viewmodel.CoinListViewModel

class MyWorker(
    appContext: Context,
    workerParams: WorkerParameters,
    private val viewModel: CoinListViewModel
) : CoroutineWorker(appContext = appContext, params = workerParams) {

    override suspend fun doWork(): Result {

        return try {
            viewModel.getCoins()
            Result.success()
        } catch (e: Exception) {
            if (runAttemptCount < 20) {
                Result.retry()
            } else {
                Result.failure()
            }
        }
    }
}