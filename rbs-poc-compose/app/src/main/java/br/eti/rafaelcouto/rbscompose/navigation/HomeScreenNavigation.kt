package br.eti.rafaelcouto.rbscompose.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.eti.rafaelcouto.rbscompose.ui.screen.HomeScreen
import br.eti.rafaelcouto.rbscompose.viewmodel.HomeViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.homeScreen(
    navController: NavController
) {
    composable<Unit> {
        val viewModel: HomeViewModel = koinViewModel()

        val state by viewModel.homeUiState.collectAsState()

        HomeScreen(
            state = state,
            onSave = {
                val user = viewModel.user
                navController.navigate(user)
            }
        )
    }
}
