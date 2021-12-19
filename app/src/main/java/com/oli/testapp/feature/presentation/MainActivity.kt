package com.oli.testapp.feature.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.oli.testapp.feature.presentation.viewmodel.CoinListViewModel
import com.oli.testapp.feature.presentation.components.CoinListScreen
import com.oli.testapp.feature.presentation.viewmodel.ViewModelFactory
import com.oli.testapp.ui.theme.TestAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    val viewModel: CoinListViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val scaffoldState = rememberScaffoldState()
            TestAppTheme {
                Scaffold(
                    scaffoldState = scaffoldState
                ) {
                    Box(
                        modifier = Modifier
                            .background(MaterialTheme.colors.background)
                    ) {
                        LaunchedEffect(key1 = true) {
                            viewModel.eventFlow.collect { event ->
                                when (event) {
                                    is CoinListViewModel.UIEvent.ShowSnackbar -> {
                                        scaffoldState.snackbarHostState.showSnackbar(
                                            message = event.message
                                        )
                                    }
                                }
                            }
                        }
                        CoinListScreen(viewModel = viewModel)
                    }
                }
            }
        }
    }
}