package br.eti.rafaelcouto.rbscompose.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import br.eti.rafaelcouto.rbscompose.model.User
import br.eti.rafaelcouto.rbscompose.ui.screen.UserScreen
import br.eti.rafaelcouto.rbscompose.viewmodel.UserViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

fun NavGraphBuilder.userScreen() {
    composable<User> { backstack ->
        val viewModel: UserViewModel = koinViewModel(parameters = {
            val user: User = backstack.toRoute()
            parametersOf(user)
        })

        val state by viewModel.userUiState.collectAsState()

        UserScreen(state = state)
    }
}
