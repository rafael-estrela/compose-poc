package br.eti.rafaelcouto.rbscompose.ui.state

import androidx.annotation.StringRes
import br.eti.rafaelcouto.rbscompose.R

data class MainUiState(
    @StringRes val title: Int = R.string.title_home,
    val hasBackButton: Boolean = false
)
