package com.oli.testapp.feature.domain.use_case

import com.oli.testapp.core.util.Resource
import com.oli.testapp.feature.domain.model.Coin
import kotlinx.coroutines.flow.Flow

interface GetCoinsUseCase {

   suspend fun getCoins(): Flow<Resource<List<Coin>>>
}