package com.oli.testapp.core.worker

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.oli.testapp.feature.presentation.viewmodel.CoinListViewModel

class MyWorkerFactory(
    private val viewModel: CoinListViewModel
) : WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        return MyWorker(
            appContext = appContext,
            workerParams = workerParameters,
            viewModel = viewModel
        )
    }
}