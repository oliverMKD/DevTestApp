package com.oli.testapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import com.oli.testapp.feature.presentation.CoinListScreen
import com.oli.testapp.feature.presentation.CoinListViewModel
import com.oli.testapp.ui.theme.TestAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val viewModel: CoinListViewModel = hiltViewModel()
            TestAppTheme {
                CoinListScreen(viewModel = viewModel)
            }
        }
    }
}