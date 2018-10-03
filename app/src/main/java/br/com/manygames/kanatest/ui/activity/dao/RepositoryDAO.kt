package br.com.manygames.kanatest.ui.activity.dao

import br.com.manygames.kanatest.model.Pull
import br.com.manygames.kanatest.model.Repository
import java.util.*

class RepositoryDAO {
    fun getRepositories(): List<Repository> {
        return repositories.clone() as List<Repository>
    }

    fun addRepositories(repos: List<Repository>?) {
        repositories.addAll(repos!!)
    }

    fun getPulls(): List<Pull> {
        return pulls.clone() as List<Pull>
    }

    fun addPulls(pullsList: List<Pull>?) {
        pulls.addAll(pullsList!!)
    }

    companion object {
        private val repositories = ArrayList<Repository>()
        private val pulls = ArrayList<Pull>()
    }

}
