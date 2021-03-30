package com.aoinc.lastday_coroutines

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GitViewModel : ViewModel() {
    val liveData: MutableLiveData<GitResult.GitResultItem> = MutableLiveData()
    val gitRetrofit = GitRetrofit()

    suspend fun makeRequest() {
        var apiData: GitResult? = null

        gitRetrofit.getRepos("aormsby").enqueue(object : Callback<List<GitResult.GitResultItem>> {
            override fun onResponse(
                call: Call<List<GitResult.GitResultItem>>,
                response: Response<List<GitResult.GitResultItem>>
            ) {
                TODO("Not yet implemented")
            }

            override fun onFailure(call: Call<List<GitResult.GitResultItem>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

    }
}