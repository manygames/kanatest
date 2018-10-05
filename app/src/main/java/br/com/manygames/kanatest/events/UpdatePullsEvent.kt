package br.com.manygames.kanatest.events

import br.com.manygames.kanatest.model.Pull

class UpdatePullsEvent(val pullsSync: List<Pull>?) {}
