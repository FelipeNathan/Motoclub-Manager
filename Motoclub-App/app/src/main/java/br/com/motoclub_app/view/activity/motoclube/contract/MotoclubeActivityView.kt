package br.com.motoclub_app.view.activity.motoclube.contract

import br.com.motoclub_app.core.contract.View

interface MotoclubeActivityView : View {

    fun onSalvar()

    fun showError(msg: String?)
}