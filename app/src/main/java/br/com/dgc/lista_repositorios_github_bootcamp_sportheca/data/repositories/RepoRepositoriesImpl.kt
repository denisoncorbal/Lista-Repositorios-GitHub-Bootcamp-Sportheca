package br.com.dgc.lista_repositorios_github_bootcamp_sportheca.data.repositories

import android.util.Log
import br.com.dgc.lista_repositorios_github_bootcamp_sportheca.core.RemoteException
import br.com.dgc.lista_repositorios_github_bootcamp_sportheca.data.services.GitHubService
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class RepoRepositoriesImpl( private val service: GitHubService) : RepoRepositories {
    override suspend fun listRepositories(user: String) = flow{
        try{
            val repoList = service.listRepositories(user)
            emit(repoList)
        } catch (ex: HttpException){
            throw RemoteException(ex.message ?: "Não foi possível fazer a busca no momento")
        }
    }

    override suspend fun listRepositoriesByFilter(user: String, filter: Int) = flow{
        try{
            val repoList = service.listRepositories(user)
            when(filter){
                1 -> {
                    emit(
                        repoList.sortedBy { it.name }
                    )
                }
                2 -> {
                    emit(
                        repoList.sortedBy { it.language }
                    )
                }
                else -> {
                    throw UnsupportedOperationException("Não foi possível identificar o filtro")
                }
            }
        } catch (ex: HttpException){
            throw RemoteException(ex.message ?: "Não foi possível fazer a busca no momento")
        }
    }
}