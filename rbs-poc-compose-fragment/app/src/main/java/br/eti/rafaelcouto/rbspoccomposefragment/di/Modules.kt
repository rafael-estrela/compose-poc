package br.eti.rafaelcouto.rbspoccomposefragment.di

import br.eti.rafaelcouto.rbspoccomposefragment.model.User
import br.eti.rafaelcouto.rbspoccomposefragment.ui.viewmodel.HomeViewModel
import br.eti.rafaelcouto.rbspoccomposefragment.ui.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::HomeViewModel)

    viewModel { (user: User) ->
        UserViewModel(user = user)
    }
}
