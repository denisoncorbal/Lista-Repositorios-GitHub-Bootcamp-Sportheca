package br.com.dgc.lista_repositorios_github_bootcamp_sportheca.domain

import br.com.dgc.lista_repositorios_github_bootcamp_sportheca.core.UseCase
import br.com.dgc.lista_repositorios_github_bootcamp_sportheca.data.model.Repo
import br.com.dgc.lista_repositorios_github_bootcamp_sportheca.data.repositories.RepoRepositories
import kotlinx.coroutines.flow.Flow

class ListUserRepositoriesUseCase (
    private val repository : RepoRepositories
        ) : UseCase<String, List<Repo>>() {
    override suspend fun execute(param: String): Flow<List<Repo>> {
        return repository.listRepositories(param)
    }
}