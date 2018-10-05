package br.com.manygames.kanatest.ui.activity.dao

import android.util.Log
import br.com.manygames.kanatest.model.Pull
import br.com.manygames.kanatest.model.Repository
import java.util.*

class RepositoryDAO {
    fun getRepositories(): ArrayList<Repository> {
        return repositories
    }

    fun addRepositories(repos: List<Repository>?) {
        Log.i("WEBER", repositories.size.toString())
        repositories.addAll(repos!!)
    }

    fun getPulls(): List<Pull> {
        return pulls.clone() as List<Pull>
    }

    fun addPulls(pullsList: List<Pull>?) {
        pulls.clear()
        pulls.addAll(pullsList!!)
    }

    companion object {
        private val repositories = ArrayList<Repository>()
        private val pulls = ArrayList<Pull>()
    }

}
