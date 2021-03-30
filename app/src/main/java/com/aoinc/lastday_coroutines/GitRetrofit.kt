package com.aoinc.lastday_coroutines

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


class GitRetrofit {

    private var gitApi: GitApi

    init {
        gitApi = createApi(createRetrofit())
    }

    private fun createApi(retrofit: Retrofit): GitApi =
        retrofit.create(GitApi::class.java)

    private fun createRetrofit(): Retrofit =
        Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

    fun getRepos(username: String): Call<List<GitResult.GitResultItem>> =
        gitApi.getRepos(username)
}

interface GitApi {
    @GET("users/{user}/repos")
    fun getRepos(@Path("user") username: String): Call<List<GitResult.GitResultItem>>
}