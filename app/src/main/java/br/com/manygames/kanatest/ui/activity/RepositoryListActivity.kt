package br.com.manygames.kanatest.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ArrayAdapter
import br.com.manygames.kanatest.R
import br.com.manygames.kanatest.model.Repository
import br.com.manygames.kanatest.retrofit.sync.RepositorySynchronizer
import br.com.manygames.kanatest.ui.activity.dao.RepositoryDAO
import br.com.manygames.kanatest.ui.adapter.RepositoryListAdapter
import kotlinx.android.synthetic.main.activity_repository_list.*

class RepositoryListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository_list)
        RepositorySynchronizer().getRepositories()
    }

    override fun onResume() {
        super.onResume()

        val reps = RepositoryDAO().getRepositories()
        val adapter = RepositoryListAdapter(reps, this)
        repository_list_listview.adapter = adapter
    }

}
