package br.eti.rafaelcouto.rbspoccomposefragment

import android.app.Application
import br.eti.rafaelcouto.rbspoccomposefragment.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class RBSPocComposeFragmentApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@RBSPocComposeFragmentApplication)
            modules(viewModelModule)
        }
    }
}
