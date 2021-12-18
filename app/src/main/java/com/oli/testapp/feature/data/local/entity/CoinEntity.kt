package com.oli.testapp.feature.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.oli.testapp.feature.domain.model.Coin

@Entity
data class CoinEntity(
    @PrimaryKey
    val dBId: Int? = null,
    val id: String?,
    val isActive: Boolean,
    val name: String?,
    val rank: Int?,
    val symbol: String?
) {
    fun toCoin(): Coin {
        return Coin(
            id = id,
            isActive = isActive,
            name = name,
            rank = rank,
            symbol = symbol
        )
    }
}
