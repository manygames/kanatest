package br.com.manygames.kanatest.retrofit.sync

import android.util.Log
import br.com.manygames.kanatest.dto.Result
import br.com.manygames.kanatest.retrofit.RetrofitInitializer
import br.com.manygames.kanatest.ui.activity.dao.RepositoryDAO
import retrofit2.Call
import retrofit2.Response

class RepositorySynchronizer {

    fun getRepositories(){
        val call = RetrofitInitializer().gitService.listRepositories(1)
        call.enqueue(getRepositoriesCallback())
    }

    private fun getRepositoriesCallback(): retrofit2.Callback<Result> {
        return object : retrofit2.Callback<Result> {
            override fun onResponse(call: Call<Result>, response: Response<Result>) {
                val repoSync = response.body()
                sync(repoSync)
            }

            override fun onFailure(call: Call<Result>, t: Throwable) {
                Log.e("onFailure: ", t.message)
            }
        }
    }

    private fun sync(repoSync: Result?) {
        RepositoryDAO().add(repoSync?.items)
    }
}