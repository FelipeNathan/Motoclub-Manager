package br.com.motoclub_app.view.fragment.motoclube.lista.contract

import br.com.motoclub_app.core.contract.Presenter

interface MotoclubesFragmentPresenter : Presenter {
    fun loadMotoclubes()

    fun solicitarEntrada(mcId: Long)
}