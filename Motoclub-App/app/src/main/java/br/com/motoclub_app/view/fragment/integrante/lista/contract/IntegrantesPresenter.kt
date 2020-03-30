package br.com.motoclub_app.view.fragment.integrante.lista.contract

import br.com.motoclub_app.core.contract.Presenter
import br.com.motoclub_app.view.fragment.Item

interface IntegrantesPresenter : Presenter {

    fun loadIntegrantes(lastItem: Item?)
}