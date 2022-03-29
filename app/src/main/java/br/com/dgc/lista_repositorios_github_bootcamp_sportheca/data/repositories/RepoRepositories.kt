package br.com.dgc.lista_repositorios_github_bootcamp_sportheca.data.repositories

import br.com.dgc.lista_repositorios_github_bootcamp_sportheca.data.model.Repo
import kotlinx.coroutines.flow.Flow

interface RepoRepositories {
    suspend fun listRepositories(user : String): Flow<List<Repo>>
}