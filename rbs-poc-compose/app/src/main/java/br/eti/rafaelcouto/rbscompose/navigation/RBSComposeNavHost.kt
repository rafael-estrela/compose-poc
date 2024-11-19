package br.eti.rafaelcouto.rbscompose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun RBSComposeNavHost(
    navController: NavHostController,
    onRouteChanged: (Route) -> Unit = {}
) {
    val state by navController.currentBackStackEntryAsState()
    state?.let {
        Route.fromPath[it.destination.route]?.let { route ->
            onRouteChanged(route)
        }
    }

    NavHost(navController = navController, startDestination = Unit) {
        homeScreen(navController)
        userScreen()
    }
}
