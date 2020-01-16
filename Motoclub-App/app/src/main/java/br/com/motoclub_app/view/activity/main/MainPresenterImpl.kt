package br.com.motoclub_app.view.activity.main

import br.com.motoclub_app.view.activity.main.contract.MainPresenter
import br.com.motoclub_app.view.activity.main.contract.MainView
import br.com.motoclub_app.repository.UserRepository
import javax.inject.Inject

class MainPresenterImpl @Inject constructor(val view: MainView) : MainPresenter {

    @Inject
    lateinit var userRepository: UserRepository

    override fun logout() {
        userRepository.removeCache()
        view.onLogout()
    }
}