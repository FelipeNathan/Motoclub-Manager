package br.com.motoclub_app.view.activity.main

import br.com.motoclub_app.model.Motoclube
import br.com.motoclub_app.repository.MotoclubeRepository
import br.com.motoclub_app.repository.UserRepository
import br.com.motoclub_app.view.activity.main.contract.MainPresenter
import br.com.motoclub_app.view.activity.main.contract.MainView
import javax.inject.Inject

class MainPresenterImpl @Inject constructor(val view: MainView) : MainPresenter {

    @Inject
    lateinit var userRepository: UserRepository

    @Inject
    lateinit var motoclubeRepository: MotoclubeRepository

    override fun logout() {
        userRepository.removeCache()
        view.onLogout()
    }

    override fun getMotoclube(): Motoclube? {

        UserRepository.loggedUser!!.motoclubeId?.let {
            return@getMotoclube motoclubeRepository.findById(it)
        }

        return null
    }
}