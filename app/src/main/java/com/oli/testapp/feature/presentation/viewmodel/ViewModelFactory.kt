package com.oli.testapp.feature.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.oli.testapp.feature.domain.use_case.GetCoinsUseCase
import javax.inject.Inject

class ViewModelFactory
@Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        CoinListViewModel(getCoinsUseCase = getCoinsUseCase) as T
}
