package br.com.motoclub_app.view.activity.motoclube.contract

import br.com.motoclub_app.core.contract.View
import br.com.motoclub_app.model.Motoclube

interface MotoclubeActivityView : View {

    fun onSave()

    fun showError(msg: String?)

    fun onLoadMotoclube(mc: Motoclube?)
}