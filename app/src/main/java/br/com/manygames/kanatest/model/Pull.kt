package br.com.manygames.kanatest.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.io.Serializable

@JsonIgnoreProperties(ignoreUnknown = true)
class Pull : Serializable {
    var body: String = ""
    var title: String = ""
    var user: PullUser? = null
    var html_url: String = ""
}