package com.oli.testapp.feature.presentation

import com.oli.testapp.feature.domain.model.Coin

data class CoinState(
    val coins: List<Coin> = emptyList(),
    val isLoading: Boolean = false
)