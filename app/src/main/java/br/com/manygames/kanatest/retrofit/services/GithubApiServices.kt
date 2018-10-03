package br.com.manygames.kanatest.retrofit.services

import br.com.manygames.kanatest.dto.Result
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApiServices {
    @GET("search/repositories?q=language:Java&sort=stars&")
    fun listRepositories(@Query("page") page: Int): Call<Result>
}
