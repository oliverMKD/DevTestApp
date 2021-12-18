package com.oli.testapp.feature.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.oli.testapp.feature.data.local.entity.CoinEntity

@Dao
interface CoinDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoins(coins: List<CoinEntity>)

    @Query("SELECT * FROM coinentity")
    suspend fun getCoins(): List<CoinEntity>

    @Query("DELETE FROM coinentity")
    suspend fun deleteCoins()
}