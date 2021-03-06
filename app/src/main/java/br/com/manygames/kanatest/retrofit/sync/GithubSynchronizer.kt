package br.com.manygames.kanatest.retrofit.sync

import android.util.Log
import br.com.manygames.kanatest.dto.RepositoryResult
import br.com.manygames.kanatest.model.Pull
import br.com.manygames.kanatest.model.Repository
import br.com.manygames.kanatest.retrofit.RetrofitInitializer
import br.com.manygames.kanatest.events.UpdatePullsEvent
import br.com.manygames.kanatest.events.UpdateRepositoriesEvent
import br.com.manygames.kanatest.ui.activity.dao.RepositoryDAO
import org.greenrobot.eventbus.EventBus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GithubSynchronizer {

    fun getRepositories(page: Int){
        val call = RetrofitInitializer().gitService.listRepositories(page)
        call.enqueue(getRepositoriesCallback())
    }

    private fun getRepositoriesCallback(): retrofit2.Callback<RepositoryResult> {
        return object : retrofit2.Callback<RepositoryResult> {

            override fun onResponse(call: Call<RepositoryResult>, response: Response<RepositoryResult>) {
                val repoSync = response.body()

                //criar uma interface de eventos, extrair metodo: notifyActivities
                val bus = EventBus.getDefault()
                bus.post(UpdateRepositoriesEvent(repoSync?.items))
            }

            override fun onFailure(call: Call<RepositoryResult>, t: Throwable) {
                Log.e("onFailure: ", t.message)
            }
        }
    }

    fun getPullsFrom(receivedRepo: Repository) {
        val call = RetrofitInitializer().gitService.listPullsFrom(receivedRepo.owner!!.login, receivedRepo.name)
        call.enqueue(getPullsCallback())
    }

    private fun getPullsCallback(): Callback<List<Pull>> {
        return object : Callback<List<Pull>> {

            override fun onResponse(call: Call<List<Pull>>, response: Response<List<Pull>>) {
                val pullsSync = response.body()

                //Usar mesmo metodo do repositories
                var bus = EventBus.getDefault()
                bus.post(UpdatePullsEvent(pullsSync))
            }

            override fun onFailure(call: Call<List<Pull>>, t: Throwable) {
                Log.e("onFailure", t.message)
            }
        }
    }
}