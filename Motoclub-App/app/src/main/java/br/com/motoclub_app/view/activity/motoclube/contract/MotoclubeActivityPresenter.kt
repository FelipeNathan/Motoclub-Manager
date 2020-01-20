package br.com.motoclub_app.view.activity.motoclube.contract

import br.com.motoclub_app.core.contract.Presenter
import br.com.motoclub_app.model.Motoclube

interface MotoclubeActivityPresenter : Presenter {

    fun salvar(motoclube: Motoclube)

    fun loadById(id: Long): Motoclube

    fun sair()

    fun solicitarEntrada(mcId: Long)
}