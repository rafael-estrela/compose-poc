package br.eti.rafaelcouto.rbscompose.navigation

import androidx.annotation.StringRes
import br.eti.rafaelcouto.rbscompose.R
import br.eti.rafaelcouto.rbscompose.model.User

sealed class Route(
    @StringRes val title: Int,
    val hasBackButton: Boolean
) {
    companion object {}

    data object Home : Route(R.string.title_home, false)
    data object User : Route(R.string.title_user, true)
}

val Route.Companion.fromPath get() = mapOf(
    Unit::class.qualifiedName to Route.Home,
    User::class.qualifiedName + "?username={username}&password={password}" to Route.User
)
