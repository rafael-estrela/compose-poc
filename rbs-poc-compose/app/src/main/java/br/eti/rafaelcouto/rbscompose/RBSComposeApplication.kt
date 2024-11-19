package br.eti.rafaelcouto.rbscompose

import android.app.Application
import br.eti.rafaelcouto.rbscompose.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class RBSComposeApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@RBSComposeApplication)
            modules(viewModelModule)
        }
    }
}
