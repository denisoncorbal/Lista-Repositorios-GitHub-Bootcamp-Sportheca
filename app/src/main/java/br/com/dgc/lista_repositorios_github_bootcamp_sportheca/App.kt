package br.com.dgc.lista_repositorios_github_bootcamp_sportheca

import android.app.Application
import br.com.dgc.lista_repositorios_github_bootcamp_sportheca.data.di.DataModule
import br.com.dgc.lista_repositorios_github_bootcamp_sportheca.domain.di.DomainModule
import br.com.dgc.lista_repositorios_github_bootcamp_sportheca.presentation.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(this@App)
        }

        DataModule.load()
        DomainModule.load()
        PresentationModule.load()
    }
}