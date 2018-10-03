package br.com.manygames.kanatest.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import br.com.manygames.kanatest.R
import br.com.manygames.kanatest.model.Repository
import br.com.manygames.kanatest.retrofit.sync.RepositorySynchronizer
import br.com.manygames.kanatest.ui.activity.dao.RepositoryDAO

class RepositoryListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository_list)
        RepositorySynchronizer().getRepositories()
    }

    override fun onResume() {
        super.onResume()
        Log.i("meuOnResume", "Entrou")
        fillRepositoryList()
    }

    private fun fillRepositoryList() {
        RepositoryDAO().list()
    }


//    private fun buscaAlunos() {
//        val call = RetroFitInicializador().alunoService.lista()
//        call.enqueue(buscaAlunosCallback())
//    }
//
//    private fun buscaAlunosCallback(): Callback<AlunoSync> {
//        return object : Callback<AlunoSync> {
//            override fun onResponse(call: Call<AlunoSync>, response: Response<AlunoSync>) {
//                val alunoSync = response.body()
//
//                sincroniza(alunoSync)
//
//                Log.i("versao", preferences.versao)
//
//                bus.post(AtualizaListaAlunoEvent())
//                sincronizaAlunosInternos()
//            }
//
//            override fun onFailure(call: Call<AlunoSync>, t: Throwable) {
//                Log.e("onFailure: ", t.message)
//                bus.post(AtualizaListaAlunoEvent())
//            }
//        }
//    }
}
