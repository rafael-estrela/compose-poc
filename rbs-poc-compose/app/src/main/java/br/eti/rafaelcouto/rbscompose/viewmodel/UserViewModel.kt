package br.eti.rafaelcouto.rbscompose.viewmodel

import androidx.lifecycle.ViewModel
import br.eti.rafaelcouto.rbscompose.model.User
import br.eti.rafaelcouto.rbscompose.ui.state.UserScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class UserViewModel(user: User) : ViewModel() {

    private val userState = MutableStateFlow(UserScreenUiState())
    val userUiState
        get() = userState.asStateFlow()

    init {
        userState.update { state ->
            state.copy(
                username = user.username,
                password = user.password
            )
        }
    }
}
