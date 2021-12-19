package com.oli.testapp.core.worker

import androidx.work.DelegatingWorkerFactory
import com.oli.testapp.feature.presentation.viewmodel.CoinListViewModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IoSchedWorkerFactory @Inject constructor(
    viewModel: CoinListViewModel
) :
    DelegatingWorkerFactory() {

    init {
        addFactory(MyWorkerFactory(viewModel))
    }
}