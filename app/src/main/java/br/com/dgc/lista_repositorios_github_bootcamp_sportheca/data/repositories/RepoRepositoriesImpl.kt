package br.com.dgc.lista_repositorios_github_bootcamp_sportheca.data.repositories

import br.com.dgc.lista_repositorios_github_bootcamp_sportheca.core.RemoteException
import br.com.dgc.lista_repositorios_github_bootcamp_sportheca.data.services.GitHubService
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class RepoRepositoriesImpl( private val service: GitHubService) : RepoRepositories {
    override suspend fun listRepositories(user: String) = flow{
        try{
            val repoList = service.ListRepositories(user)
            emit(repoList)
        } catch (ex: HttpException){
            throw RemoteException(ex.message ?: "Não foi possível fazer a busca no momento")
        }
    }
}