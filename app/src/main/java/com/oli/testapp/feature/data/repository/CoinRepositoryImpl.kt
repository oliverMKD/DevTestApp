package com.oli.testapp.feature.data.repository

import com.oli.testapp.core.util.Resource
import com.oli.testapp.feature.data.local.CoinDao
import com.oli.testapp.feature.data.remote.CoinPaprikaApi
import com.oli.testapp.feature.data.remote.dto.toCoinEntity
import com.oli.testapp.feature.domain.model.Coin
import com.oli.testapp.feature.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class CoinRepositoryImpl(
    private val api: CoinPaprikaApi,
    private val dao: CoinDao
) : CoinRepository {

    override fun getCoinsList(): Flow<Resource<List<Coin>>> = flow {

        emit(Resource.Loading())
        val coins = dao.getCoins().map {
            it.toCoin()
        }
        emit(Resource.Loading(data = coins))

        try {
            val coinsFromServer = api.getCoins()
            dao.deleteCoins(coins = coinsFromServer.map { it.toCoinEntity() })
            dao.insertCoins(coins = coinsFromServer.map { it.toCoinEntity() })

        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = "Oops, something went wrong!",
                    data = coins
                )
            )

        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Couldn't reach server, check your internet connection.",
                    data = coins
                )
            )
        }
        val newCoins = dao.getCoins().map { it.toCoin() }
        emit(Resource.Success(data = newCoins))
    }
}