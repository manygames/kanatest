package br.com.manygames.kanatest.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.manygames.kanatest.R
import br.com.manygames.kanatest.events.UpdatePullsEvent
import br.com.manygames.kanatest.model.Pull
import br.com.manygames.kanatest.model.Repository
import br.com.manygames.kanatest.retrofit.sync.GithubSynchronizer
import br.com.manygames.kanatest.ui.activity.dao.RepositoryDAO
import br.com.manygames.kanatest.ui.adapter.PullListAdapter
import kotlinx.android.synthetic.main.activity_repository_details.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class RepositoryDetailsActivity : AppCompatActivity() {
    private var bus: EventBus? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository_details)
        bus = EventBus.getDefault()
        val receivedRepo: Repository = intent.getSerializableExtra("repository") as Repository
        GithubSynchronizer().getPullsFrom(receivedRepo);
    }

    override fun onResume() {
        super.onResume()
        bus!!.register(this)
        //loadPulls()
    }

    private fun openClickedPullOnTheWeb(clickedPull: Pull) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setData(Uri.parse(clickedPull.html_url))
        startActivity(intent)
    }

    override fun onPause() {
        super.onPause()
        bus!!.unregister(this)
    }

    private var pullsAapter: PullListAdapter? = null

    fun loadPulls(pulls: List<Pull>) {

        if(pullsAapter == null)
            pullsAapter = PullListAdapter(pulls, this)

        pullsAapter!!.clear()
        RepositoryDAO().addPulls(pulls)

        with(repository_details_listview) {
            adapter = pullsAapter
            setOnItemClickListener { _, _, position, id ->
                val clickedPull = pulls[position]
                openClickedPullOnTheWeb(clickedPull)
            }
        }
    }

    @Subscribe
    fun updatePullsEvent(event: UpdatePullsEvent){
        loadPulls(event.pullsSync!!)
    }

}
