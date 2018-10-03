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
        call.enqueue(buscaAlunosCallback())
    }

    private fun buscaAlunosCallback(): retrofit2.Callback<Result> {
        return object : retrofit2.Callback<Result> {
            override fun onResponse(call: Call<Result>, response: Response<Result>) {
                Log.i("meusync", "buscaAlunosCallback")
                val repoSync = response.body()
                sync(repoSync)
            }

            override fun onFailure(call: Call<Result>, t: Throwable) {
                Log.e("onFailure: ", t.message)
                //bus.post(AtualizaListaAlunoEvent())
            }
        }
    }

    private fun sync(repoSync: Result?) {
        RepositoryDAO().add(repoSync?.items)
        Log.i("meusync", "ENTROU!" + repoSync?.items!![0].description)
    }
}