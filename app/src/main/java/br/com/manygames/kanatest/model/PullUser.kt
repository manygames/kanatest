package br.com.manygames.kanatest.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class PullUser {
    var login: String = ""
}
