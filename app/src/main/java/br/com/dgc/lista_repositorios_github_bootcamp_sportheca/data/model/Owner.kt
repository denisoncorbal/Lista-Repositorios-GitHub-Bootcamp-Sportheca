package br.com.dgc.lista_repositorios_github_bootcamp_sportheca.data.model

import com.google.gson.annotations.SerializedName

data class Owner (
    val login: String,
    @SerializedName("avatar_url")
    val avatarURL: String
)
