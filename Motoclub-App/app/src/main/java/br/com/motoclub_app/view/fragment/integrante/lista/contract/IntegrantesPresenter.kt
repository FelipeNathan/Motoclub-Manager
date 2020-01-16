package br.com.motoclub_app.view.fragment.integrante.lista.contract

import br.com.motoclub_app.core.contract.Presenter

interface IntegrantesPresenter : Presenter {

    fun loadIntegrantes()
}