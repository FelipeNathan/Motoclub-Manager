package br.com.motoclub_app.view.activity.main.contract

import br.com.motoclub_app.core.contract.View
import br.com.motoclub_app.model.Motoclube

interface MainView : View {

    fun onLogout()

    fun onGetMotoclube(motoclube: Motoclube)
}