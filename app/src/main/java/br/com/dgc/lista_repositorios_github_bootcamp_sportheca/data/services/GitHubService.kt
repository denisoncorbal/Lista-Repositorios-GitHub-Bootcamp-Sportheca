package br.com.dgc.lista_repositorios_github_bootcamp_sportheca.data.services

import br.com.dgc.lista_repositorios_github_bootcamp_sportheca.data.model.Repo
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {
    @GET("users/{user}/repos")
    suspend fun listRepositories(@Path("user") user : String): List<Repo>
}