package br.com.manygames.kanatest.retrofit.sync

import android.util.Log
import br.com.manygames.kanatest.dto.RepositoryResult
import br.com.manygames.kanatest.model.Pull
import br.com.manygames.kanatest.model.Repository
import br.com.manygames.kanatest.retrofit.RetrofitInitializer
import br.com.manygames.kanatest.ui.activity.dao.RepositoryDAO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GithubSynchronizer {

    fun getRepositories(){
        val call = RetrofitInitializer().gitService.listRepositories(1)
        call.enqueue(getRepositoriesCallback())
    }

    private fun getRepositoriesCallback(): retrofit2.Callback<RepositoryResult> {
        return object : retrofit2.Callback<RepositoryResult> {

            override fun onResponse(call: Call<RepositoryResult>, response: Response<RepositoryResult>) {
                val repoSync = response.body()
                sync(repoSync)
            }

            override fun onFailure(call: Call<RepositoryResult>, t: Throwable) {
                Log.e("onFailure: ", t.message)
            }
        }
    }

    private fun sync(repoSync: RepositoryResult?) {
        RepositoryDAO().addRepositories(repoSync?.items)
    }

    fun getPullsFrom(receivedRepo: Repository) {
        val call = RetrofitInitializer().gitService.listPullsFrom(receivedRepo.owner!!.login, receivedRepo.name)
        call.enqueue(getPullsCallback())
    }

    private fun getPullsCallback(): Callback<List<Pull>> {
        return object : Callback<List<Pull>> {

            override fun onResponse(call: Call<List<Pull>>, response: Response<List<Pull>>) {
                val pullSync = response.body()
                sync(pullSync)
            }

            override fun onFailure(call: Call<List<Pull>>, t: Throwable) {
                Log.e("onFailure", t.message)
            }
        }
    }

    private fun sync(pullSync: List<Pull>?) {
        RepositoryDAO().addPulls(pullSync)
    }
}