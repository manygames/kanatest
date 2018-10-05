package br.com.manygames.kanatest.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.manygames.kanatest.R
import br.com.manygames.kanatest.events.UpdateRepositoriesEvent
import br.com.manygames.kanatest.model.Repository
import br.com.manygames.kanatest.retrofit.sync.GithubSynchronizer
import br.com.manygames.kanatest.ui.activity.dao.RepositoryDAO
import br.com.manygames.kanatest.ui.adapter.RepositoryListAdapter
import kotlinx.android.synthetic.main.activity_repository_list.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class RepositoryListActivity : AppCompatActivity() {

    private var bus: EventBus? = null
    private val FIRST_PAGE = 1
    private val SCREEN__TITLE = "Github Java Pop"

    private var repositoriesAdapter: RepositoryListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = SCREEN__TITLE
        bus = EventBus.getDefault()
        setContentView(R.layout.activity_repository_list)
        GithubSynchronizer().getRepositories(FIRST_PAGE)
        setupViews()
    }

    private fun setupViews() {
        val repositories = RepositoryDAO().getRepositories()
        repositoriesAdapter = RepositoryListAdapter(repositories, this)
        with(repository_list_listview) {
            adapter = repositoriesAdapter
            setOnItemClickListener { _, _, position, id ->
                val clickedRep = repositories[position]
                goToClickedRep(clickedRep)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        bus!!.register(this)
    }

    fun loadRepositories(reps: ArrayList<Repository>) {
        RepositoryDAO().addRepositories(reps)
        repositoriesAdapter!!.notifyDataSetChanged()
    }

    private fun goToClickedRep(clickedRep: Repository) {
        val intent = Intent(this@RepositoryListActivity, RepositoryDetailsActivity::class.java)
        intent.putExtra("repository", clickedRep)
        startActivity(intent)
    }

    override fun onPause() {
        super.onPause()
        bus!!.unregister(this)
    }

    @Subscribe
    fun updateRepositoriesEvent(event: UpdateRepositoriesEvent) {
        loadRepositories(event!!.items!!)
    }
}
