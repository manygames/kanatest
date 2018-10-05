package br.com.manygames.kanatest.ui.activity.dao

import br.com.manygames.kanatest.model.Pull
import br.com.manygames.kanatest.model.Repository
import java.util.*

class RepositoryDAO {
    fun getRepositories(): ArrayList<Repository> {
        return repositories
    }

    fun addRepositories(repos: List<Repository>?) {
        repositories.addAll(repos!!)
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
