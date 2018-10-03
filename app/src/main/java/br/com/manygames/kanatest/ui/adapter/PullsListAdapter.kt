package br.com.manygames.kanatest.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import br.com.manygames.kanatest.R
import br.com.manygames.kanatest.model.Pull
import kotlinx.android.synthetic.main.pull_item.view.*

class PullsListAdapter(private val pulls: List<Pull>, private val context: Context) : BaseAdapter(){

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        val createdView = LayoutInflater.from(context).inflate(R.layout.pull_item, parent, false)

        createdView.pull_item_titulo_pull.text = pulls[position].title
        createdView.pull_item_body.text = pulls[position].body
        createdView.pull_item_username.text = pulls[position].user!!.login
        createdView.pull_item_data.text = pulls[position].created_at

        return createdView
    }

    override fun getItem(position: Int): Any {
        return pulls[position]
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return pulls.size
    }

}
