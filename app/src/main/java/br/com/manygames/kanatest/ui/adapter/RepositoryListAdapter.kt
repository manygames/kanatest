package br.com.manygames.kanatest.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import br.com.manygames.kanatest.R
import br.com.manygames.kanatest.model.Repository
import br.com.manygames.kanatest.retrofit.sync.GithubSynchronizer
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.repository_item.view.*

class RepositoryListAdapter (private val repositories: ArrayList<Repository>,
                             private val context: Context): BaseAdapter(){

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        val createdView = LayoutInflater.from(context).inflate(R.layout.repository_item, parent, false)

        createdView.repository_item_description.text = repositories[position].description
        createdView.repository_item_repo_name.text = repositories[position].name
        createdView.repository_item_forks.text = repositories[position].forks.toString()
        createdView.repository_item_stars.text = repositories[position].stargazers_count.toString()
        Picasso.get().load(repositories[position].owner!!.avatar_url).into(createdView.repository_item_user_image)
        createdView.repository_item_username.text = repositories[position].owner!!.login

        if(position == getCount() -3) {
            nextPage()
        }
        return createdView
    }

    override fun getItem(position: Int): Any {
        return repositories[position]
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return repositories.size
    }

    private fun nextPage(){
        GithubSynchronizer().getRepositories(++page)
    }

    companion object {
        private var page: Int = 1
    }

}