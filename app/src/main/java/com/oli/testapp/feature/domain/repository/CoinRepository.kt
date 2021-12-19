package com.oli.testapp.feature.domain.repository

import com.oli.testapp.core.util.Resource
import com.oli.testapp.feature.domain.model.Coin
import kotlinx.coroutines.flow.Flow

interface CoinRepository {

    fun getCoinsList(): Flow<Resource<List<Coin>>>
}