package br.com.manygames.kanatest.ui.activity.dao

import br.com.manygames.kanatest.model.Repository
import java.util.*

class RepositoryDAO {
    fun getRepositories(): List<Repository> {
        return repositories.clone() as List<Repository>
    }

    fun add(repos: List<Repository>?) {
        repositories.addAll(repos!!)
    }

    companion object {
        private val repositories = ArrayList<Repository>()
    }

}
