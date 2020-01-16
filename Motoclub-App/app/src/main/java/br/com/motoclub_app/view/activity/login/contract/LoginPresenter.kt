package br.com.motoclub_app.view.activity.login.contract

import br.com.motoclub_app.core.contract.Presenter

interface LoginPresenter : Presenter {

    fun onLogin(email: String, password: String)

    fun onCreate()
}