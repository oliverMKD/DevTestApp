package com.oli.testapp.feature.domain.use_case

import com.oli.testapp.core.util.Resource
import com.oli.testapp.feature.domain.model.Coin
import com.oli.testapp.feature.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow

class GetCoinsUseCase(
    private val repository: CoinRepository
) {
    operator fun invoke(): Flow<Resource<List<Coin>>> {
        return repository.getCoinsList()
    }
}