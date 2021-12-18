package com.oli.testapp.feature.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oli.testapp.core.util.Resource
import com.oli.testapp.feature.domain.use_case.GetCoinsUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
) : ViewModel() {

    private val _state = mutableStateOf(CoinState())
    val state: State<CoinState> = _state

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun getCoins() = viewModelScope.launch {
        getCoinsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = state.value.copy(
                        coins = result.data ?: emptyList(),
                        isLoading = false
                    )
                }

                is Resource.Error -> {
                    _state.value = state.value.copy(
                        coins = result.data ?: emptyList(),
                        isLoading = false
                    )
                    _eventFlow.emit(
                        UIEvent.ShowSnackbar(
                            result.message ?: "Unknown error"
                        )
                    )
                }

                is Resource.Loading -> {
                    _state.value = state.value.copy(
                        coins = result.data ?: emptyList(),
                        isLoading = true
                    )
                }
            }
        }.launchIn(this)
    }
}

sealed class UIEvent {
    data class ShowSnackbar(val message: String) : UIEvent()
}