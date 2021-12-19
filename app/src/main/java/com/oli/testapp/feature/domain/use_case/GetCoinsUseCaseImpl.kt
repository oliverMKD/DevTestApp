package com.oli.testapp.feature.domain.use_case

import com.oli.testapp.core.util.Resource
import com.oli.testapp.feature.domain.model.Coin
import com.oli.testapp.feature.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow

class GetCoinsUseCaseImpl(
    private val repository: CoinRepository
) : GetCoinsUseCase {

    override suspend fun getCoins(): Flow<Resource<List<Coin>>> =
        repository.getCoinsList()

}