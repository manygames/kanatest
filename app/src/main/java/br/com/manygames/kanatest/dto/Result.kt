package br.com.manygames.kanatest.dto

import br.com.manygames.kanatest.model.Repository

class Result () {
    var total_count: Int = 0
    var incomplete_results: Boolean = false
    var items: List<Repository>? = null
}