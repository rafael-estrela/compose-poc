package br.eti.rafaelcouto.rbscompose.viewmodel

import androidx.lifecycle.ViewModel
import br.eti.rafaelcouto.rbscompose.navigation.Route
import br.eti.rafaelcouto.rbscompose.ui.state.MainUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel : ViewModel() {

    private val mainState = MutableStateFlow(MainUiState())
    val state get() = mainState.asStateFlow()

    fun updateState(route: Route) {
        mainState.update {
            it.copy(
                title = route.title,
                hasBackButton = route.hasBackButton
            )
        }
    }
}
