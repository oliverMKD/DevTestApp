package com.oli.testapp.feature.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.oli.testapp.feature.presentation.viewmodel.CoinListViewModel

@Composable
fun CoinListScreen(
    viewModel: CoinListViewModel
) {
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()

    Scaffold(scaffoldState = scaffoldState) {
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                state.coins?.let {
                    items(it.size) { i ->
                        val coin = state.coins[i]
                        CoinListItem(
                            coin = coin
                        )
                    }
                }
            }
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}