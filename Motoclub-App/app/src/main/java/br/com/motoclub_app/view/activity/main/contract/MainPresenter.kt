package br.com.motoclub_app.view.activity.main.contract

import br.com.motoclub_app.core.contract.Presenter
import br.com.motoclub_app.model.Motoclube

interface MainPresenter : Presenter {

    fun logout()

    fun getMotoclube(): Motoclube?
}