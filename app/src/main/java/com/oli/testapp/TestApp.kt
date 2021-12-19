package com.oli.testapp

import android.app.Application
import androidx.work.*
import com.oli.testapp.core.worker.MyWorker
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import timber.log.Timber.DebugTree
import java.util.concurrent.TimeUnit
import javax.inject.Inject


@HiltAndroidApp
class TestApp : Application(), Configuration.Provider {

    @Inject
    lateinit var workerConfiguration: Configuration

    override fun getWorkManagerConfiguration(): Configuration {
        return workerConfiguration
    }

    private fun scheduleFetchData() {
        val coinDataWorker = PeriodicWorkRequestBuilder<MyWorker>(
            30, TimeUnit.MINUTES
        ).setBackoffCriteria(
            BackoffPolicy.LINEAR,
            PeriodicWorkRequest.MIN_BACKOFF_MILLIS,
            TimeUnit.MILLISECONDS)
            .build()

        WorkManager.getInstance(applicationContext)
            .enqueueUniquePeriodicWork(
                "uniqueDataWorker",
                ExistingPeriodicWorkPolicy.REPLACE,
                coinDataWorker
            )
    }

    override fun onCreate() {
        super.onCreate()

        scheduleFetchData()

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }
}