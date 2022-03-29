package br.com.dgc.lista_repositorios_github_bootcamp_sportheca.domain.di

import br.com.dgc.lista_repositorios_github_bootcamp_sportheca.domain.ListUserRepositoriesUseCase
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object DomainModule {
    fun load(){
        loadKoinModules(useCaseModule())
    }

    private fun useCaseModule(): Module {
        return module{
            factory {
                ListUserRepositoriesUseCase(get())
            }
        }
    }
}