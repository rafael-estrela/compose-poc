package br.eti.rafaelcouto.rbscompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import br.eti.rafaelcouto.rbscompose.ui.state.MainUiState

@Composable
fun RBSComposeNavHost(
    navController: NavHostController,
    onScreenChanged: (MainUiState) -> Unit = {}
) {
    NavHost(navController = navController, startDestination = Unit) {
        homeScreen(navController, onScreenChanged)
        userScreen(onScreenChanged)
    }
}
