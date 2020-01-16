package br.com.motoclub_app.view.fragment.motoclube.lista.contract

import br.com.motoclub_app.core.contract.Presenter

interface MotoclubesPresenter : Presenter {

    fun loadMotoclubes()
}