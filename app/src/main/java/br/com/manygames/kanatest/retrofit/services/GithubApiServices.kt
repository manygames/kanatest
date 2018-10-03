package br.com.manygames.kanatest.retrofit.services

import br.com.manygames.kanatest.dto.RepositoryResult
import br.com.manygames.kanatest.model.Pull
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApiServices {
    @GET("search/repositories?q=language:Java&sort=stars&")
    fun listRepositories(@Query("page") page: Int): Call<RepositoryResult>


    @GET("repos/{creator}/{repo}/pulls")
    fun listPullsFrom(@Path("creator")creator: String, @Path("repo") repository: String): Call<List<Pull>>
}
