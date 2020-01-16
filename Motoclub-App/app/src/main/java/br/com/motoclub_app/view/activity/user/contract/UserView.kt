package br.com.motoclub_app.view.activity.user.contract

import br.com.motoclub_app.core.contract.View

interface UserView : View {

    fun onSalvar()

    fun showError(msg: String?)
}