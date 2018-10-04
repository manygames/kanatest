package br.com.manygames.kanatest.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.io.Serializable

@JsonIgnoreProperties(ignoreUnknown = true)
class RepositoryOwner : Serializable {
    var login: String = ""
    var avatar_url: String = ""
}
