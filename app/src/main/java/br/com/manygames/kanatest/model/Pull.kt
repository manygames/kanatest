package br.com.manygames.kanatest.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class Pull {
    var body: String = ""
    var created_at: String = ""
    var title: String = ""
    var user: PullUser? = null
}