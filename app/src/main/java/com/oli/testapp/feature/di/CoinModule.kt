package com.oli.testapp.feature.di

import android.app.Application
import androidx.room.Room
import androidx.work.Configuration
import com.oli.testapp.core.worker.IoSchedWorkerFactory
import com.oli.testapp.feature.data.local.CoinDatabase
import com.oli.testapp.feature.data.remote.CoinPaprikaApi
import com.oli.testapp.feature.data.repository.CoinRepositoryImpl
import com.oli.testapp.feature.domain.repository.CoinRepository
import com.oli.testapp.feature.domain.use_case.GetCoinsUseCase
import com.oli.testapp.feature.domain.use_case.GetCoinsUseCaseImpl
import com.oli.testapp.feature.presentation.viewmodel.ViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoinModule {

    @Provides
    @Singleton
    fun providePaprikaApi(): CoinPaprikaApi {
        return Retrofit.Builder()
            .baseUrl(CoinPaprikaApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinPaprikaApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinDatabase(
        app: Application
    ): CoinDatabase {
        return Room.databaseBuilder(
            app, CoinDatabase::class.java, "coin_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideCoinRepository(
        db: CoinDatabase,
        api: CoinPaprikaApi
    ): CoinRepository {
        return CoinRepositoryImpl(api, db.dao)
    }

    @Provides
    @Singleton
    fun provideGetCoinsUseCase(
        repository: CoinRepository
    ): GetCoinsUseCase {
        return GetCoinsUseCaseImpl(repository)
    }

    @Singleton
    @Provides
    fun provideWorkManagerConfiguration(
        ioSchedWorkerFactory: IoSchedWorkerFactory
    ): Configuration {
        return Configuration.Builder()
            .setMinimumLoggingLevel(android.util.Log.DEBUG)
            .setWorkerFactory(ioSchedWorkerFactory)
            .build()
    }

    @Provides
    @Singleton
    fun providesViewModelFactory(
        getCoinsUseCase: GetCoinsUseCase
    ): ViewModelFactory {
        return ViewModelFactory(getCoinsUseCase = getCoinsUseCase)
    }
}