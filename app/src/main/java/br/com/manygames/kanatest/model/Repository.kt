package br.com.manygames.kanatest.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class Repository {
    var id: Int = 0
    var name: String = ""
    var description: String = ""
    //var authorName: String = ""
    //var authorPhoto: String = ""
    var stargazers_count: Int = 0
    var forks: Int = 0
}
