package br.com.motoclub_app.view.activity.login.contract

import br.com.motoclub_app.core.contract.View

interface LoginView : View {

    fun onLogin()

    fun showMessage(msg: String)
}