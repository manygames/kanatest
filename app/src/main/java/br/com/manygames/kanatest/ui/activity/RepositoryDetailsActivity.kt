package br.com.manygames.kanatest.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.manygames.kanatest.R
import br.com.manygames.kanatest.model.Repository
import br.com.manygames.kanatest.retrofit.sync.GithubSynchronizer
import br.com.manygames.kanatest.ui.activity.dao.RepositoryDAO
import br.com.manygames.kanatest.ui.adapter.PullsListAdapter
import kotlinx.android.synthetic.main.activity_repository_details.*

class RepositoryDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository_details)
        val receivedRepo: Repository = intent.getSerializableExtra("repository") as Repository
        GithubSynchronizer().getPullsFrom(receivedRepo);
    }

    override fun onResume() {
        super.onResume()
        val pulls = RepositoryDAO().getPulls()
        val pullsAapter = PullsListAdapter(pulls, this)
        repository_details_listview.adapter = pullsAapter
    }
}
