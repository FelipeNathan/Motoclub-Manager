package br.com.motoclub_app.interactor

import br.com.motoclub_app.repository.UserRepository
import javax.inject.Inject

class MotoclubeInteractor @Inject constructor() {

    @Inject
    lateinit var userRepository: UserRepository

    fun requestEntrance(mcId: Long) {
        UserRepository.loggedUser!!.motoclubeId = mcId
        userRepository.save(UserRepository.loggedUser!!)
        userRepository.setCache(UserRepository.loggedUser!!)
    }
}